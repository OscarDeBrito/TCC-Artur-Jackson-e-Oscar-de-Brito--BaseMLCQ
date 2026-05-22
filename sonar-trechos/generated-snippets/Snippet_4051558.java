class TestStreamEvaluatorFactory implements TransformEvaluatorFactory {
  private final EvaluationContext evaluationContext;

  TestStreamEvaluatorFactory(EvaluationContext evaluationContext) {
    this.evaluationContext = evaluationContext;
  }

  @Nullable
  @Override
  public <InputT> TransformEvaluator<InputT> forApplication(
      AppliedPTransform<?, ?, ?> application, CommittedBundle<?> inputBundle) {
    return createEvaluator((AppliedPTransform) application);
  }

  @Override
  public void cleanup() throws Exception {}

  /**
   * Returns the evaluator for the provided application of {@link TestStream}, or null if it is
   * already in use.
   *
   * <p>The documented behavior of {@link TestStream} requires the output of one event to travel
   * completely through the pipeline before any additional event, so additional instances that have
   * a separate collection of events cannot be created.
   */
  private <InputT, OutputT> TransformEvaluator<? super InputT> createEvaluator(
      AppliedPTransform<PBegin, PCollection<OutputT>, TestStream<OutputT>> application) {
    return (TransformEvaluator<InputT>) new Evaluator<>(application, evaluationContext);
  }

  private static class Evaluator<T> implements TransformEvaluator<TestStreamIndex<T>> {
    private final AppliedPTransform<PBegin, PCollection<T>, TestStream<T>> application;
    private final EvaluationContext context;
    private final StepTransformResult.Builder resultBuilder;

    private Evaluator(
        AppliedPTransform<PBegin, PCollection<T>, TestStream<T>> application,
        EvaluationContext context) {
      this.application = application;
      this.context = context;
      this.resultBuilder = StepTransformResult.withoutHold(application);
    }

    @Override
    public void processElement(WindowedValue<TestStreamIndex<T>> element) throws Exception {
      TestStreamIndex<T> streamIndex = element.getValue();
      List<Event<T>> events = streamIndex.getTestStream().getEvents();
      int index = streamIndex.getIndex();
      Instant watermark = element.getTimestamp();
      Event<T> event = events.get(index);

      if (event.getType().equals(EventType.ELEMENT)) {
        UncommittedBundle<T> bundle =
            context.createBundle(
                (PCollection<T>) Iterables.getOnlyElement(application.getOutputs().values()));
        for (TimestampedValue<T> elem : ((ElementEvent<T>) event).getElements()) {
          bundle.add(
              WindowedValue.timestampedValueInGlobalWindow(elem.getValue(), elem.getTimestamp()));
        }
        resultBuilder.addOutput(bundle);
      }

      if (event.getType().equals(EventType.WATERMARK)) {
        watermark = ((WatermarkEvent<T>) event).getWatermark();
      }

      if (event.getType().equals(EventType.PROCESSING_TIME)) {
        ((TestClock) context.getClock())
            .advance(((ProcessingTimeEvent<T>) event).getProcessingTimeAdvance());
      }

      TestStreamIndex<T> next = streamIndex.next();
      if (next.getIndex() < events.size()) {
        resultBuilder.addUnprocessedElements(
            Collections.singleton(WindowedValue.timestampedValueInGlobalWindow(next, watermark)));
      }
    }

    @Override
    public TransformResult<TestStreamIndex<T>> finishBundle() throws Exception {
      return resultBuilder.build();
    }
  }

  @VisibleForTesting
  static class TestClock implements Clock {
    private final AtomicReference<Instant> currentTime =
        new AtomicReference<>(BoundedWindow.TIMESTAMP_MIN_VALUE);

    public void advance(Duration amount) {
      Instant now = currentTime.get();
      currentTime.compareAndSet(now, now.plus(amount));
    }

    @Override
    public Instant now() {
      return currentTime.get();
    }
  }

  private static class TestClockSupplier implements Supplier<Clock> {
    @Override
    public Clock get() {
      return new TestClock();
    }
  }

  static class DirectTestStreamFactory<T>
      implements PTransformOverrideFactory<
          PBegin, PCollection<T>, PTransform<PBegin, PCollection<T>>> {
    private final DirectRunner runner;

    DirectTestStreamFactory(DirectRunner runner) {
      this.runner = runner;
    }

    @Override
    public PTransformReplacement<PBegin, PCollection<T>> getReplacementTransform(
        AppliedPTransform<PBegin, PCollection<T>, PTransform<PBegin, PCollection<T>>> transform) {
      try {
        return PTransformReplacement.of(
            transform.getPipeline().begin(),
            new DirectTestStream<>(runner, TestStreamTranslation.getTestStream(transform)));
      } catch (IOException exc) {
        throw new RuntimeException(
            String.format(
                "Transform could not be converted to %s", TestStream.class.getSimpleName()),
            exc);
      }
    }

    @Override
    public Map<PValue, ReplacementOutput> mapOutputs(
        Map<TupleTag<?>, PValue> outputs, PCollection<T> newOutput) {
      return ReplacementOutputs.singleton(outputs, newOutput);
    }

    static final String DIRECT_TEST_STREAM_URN = "urn:beam:directrunner:transforms:test_stream:v1";

    static class DirectTestStream<T> extends PTransform<PBegin, PCollection<T>> {
      private final transient DirectRunner runner;
      private final TestStream<T> original;

      @VisibleForTesting
      DirectTestStream(DirectRunner runner, TestStream<T> transform) {
        this.runner = runner;
        this.original = transform;
      }

      @Override
      public PCollection<T> expand(PBegin input) {
        runner.setClockSupplier(new TestClockSupplier());
        return PCollection.createPrimitiveOutputInternal(
            input.getPipeline(),
            WindowingStrategy.globalDefault(),
            IsBounded.UNBOUNDED,
            original.getValueCoder());
      }
    }
  }

  static class InputProvider<T> implements RootInputProvider<T, TestStreamIndex<T>, PBegin> {
    private final EvaluationContext evaluationContext;

    InputProvider(EvaluationContext evaluationContext) {
      this.evaluationContext = evaluationContext;
    }

    @Override
    public Collection<CommittedBundle<TestStreamIndex<T>>> getInitialInputs(
        AppliedPTransform<PBegin, PCollection<T>, PTransform<PBegin, PCollection<T>>> transform,
        int targetParallelism) {

      // This will always be run on an execution-time transform, so it can be downcast
      DirectTestStreamFactory.DirectTestStream<T> testStream =
          (DirectTestStreamFactory.DirectTestStream<T>) transform.getTransform();

      CommittedBundle<TestStreamIndex<T>> initialBundle =
          evaluationContext
              .<TestStreamIndex<T>>createRootBundle()
              .add(WindowedValue.valueInGlobalWindow(TestStreamIndex.of(testStream.original)))
              .commit(BoundedWindow.TIMESTAMP_MAX_VALUE);
      return Collections.singleton(initialBundle);
    }
  }

  @AutoValue
  abstract static class TestStreamIndex<T> {
    static <T> TestStreamIndex<T> of(TestStream<T> stream) {
      return new AutoValue_TestStreamEvaluatorFactory_TestStreamIndex<>(stream, 0);
    }

    abstract TestStream<T> getTestStream();

    abstract int getIndex();

    TestStreamIndex<T> next() {
      return new AutoValue_TestStreamEvaluatorFactory_TestStreamIndex<>(
          getTestStream(), getIndex() + 1);
    }
  }
}
