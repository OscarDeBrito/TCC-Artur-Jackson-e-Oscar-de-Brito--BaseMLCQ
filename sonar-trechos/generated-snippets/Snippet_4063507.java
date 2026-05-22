public static class PayloadTranslator
      implements PTransformTranslation.TransformPayloadTranslator<ParDoSingle<?, ?>> {
    public static PTransformTranslation.TransformPayloadTranslator create() {
      return new PayloadTranslator();
    }

    private PayloadTranslator() {}

    @Override
    public String getUrn(ParDoSingle<?, ?> transform) {
      return PAR_DO_TRANSFORM_URN;
    }

    @Override
    public RunnerApi.FunctionSpec translate(
        AppliedPTransform<?, ?, ParDoSingle<?, ?>> transform, SdkComponents components)
        throws IOException {
      RunnerApi.ParDoPayload payload = payloadForParDoSingle(transform, components);

      return RunnerApi.FunctionSpec.newBuilder()
          .setUrn(PAR_DO_TRANSFORM_URN)
          .setPayload(payload.toByteString())
          .build();
    }

    private static RunnerApi.ParDoPayload payloadForParDoSingle(
        final AppliedPTransform<?, ?, ParDoSingle<?, ?>> transform, SdkComponents components)
        throws IOException {
      final ParDoSingle<?, ?> parDo = transform.getTransform();
      final DoFn<?, ?> doFn = parDo.getFn();
      final DoFnSignature signature = DoFnSignatures.getSignature(doFn.getClass());
      checkArgument(
          !signature.processElement().isSplittable(),
          String.format(
              "Not expecting a splittable %s: should have been overridden",
              ParDoSingle.class.getSimpleName()));

      // TODO: Is there a better way to do this?
      Set<String> allInputs =
          transform.getInputs().keySet().stream().map(TupleTag::getId).collect(Collectors.toSet());
      Set<String> sideInputs =
          parDo.getSideInputs().stream()
              .map(s -> s.getTagInternal().getId())
              .collect(Collectors.toSet());
      Set<String> timerInputs = signature.timerDeclarations().keySet();
      String mainInputName =
          Iterables.getOnlyElement(Sets.difference(allInputs, Sets.union(sideInputs, timerInputs)));
      PCollection<?> mainInput =
          (PCollection<?>) transform.getInputs().get(new TupleTag<>(mainInputName));

      final DoFnSchemaInformation doFnSchemaInformation =
          ParDo.getDoFnSchemaInformation(doFn, mainInput);

      return ParDoTranslation.payloadForParDoLike(
          new ParDoTranslation.ParDoLike() {
            @Override
            public RunnerApi.SdkFunctionSpec translateDoFn(SdkComponents newComponents) {
              return ParDoTranslation.translateDoFn(
                  parDo.getFn(), parDo.getMainOutputTag(), doFnSchemaInformation, newComponents);
            }

            @Override
            public List<RunnerApi.Parameter> translateParameters() {
              return ParDoTranslation.translateParameters(
                  signature.processElement().extraParameters());
            }

            @Override
            public Map<String, RunnerApi.SideInput> translateSideInputs(SdkComponents components) {
              return ParDoTranslation.translateSideInputs(parDo.getSideInputs(), components);
            }

            @Override
            public Map<String, RunnerApi.StateSpec> translateStateSpecs(SdkComponents components)
                throws IOException {
              Map<String, RunnerApi.StateSpec> stateSpecs = new HashMap<>();
              for (Map.Entry<String, DoFnSignature.StateDeclaration> state :
                  signature.stateDeclarations().entrySet()) {
                RunnerApi.StateSpec spec =
                    ParDoTranslation.translateStateSpec(
                        getStateSpecOrThrow(state.getValue(), doFn), components);
                stateSpecs.put(state.getKey(), spec);
              }
              return stateSpecs;
            }

            @Override
            public Map<String, RunnerApi.TimerSpec> translateTimerSpecs(
                SdkComponents newComponents) {
              Map<String, RunnerApi.TimerSpec> timerSpecs = new HashMap<>();
              for (Map.Entry<String, DoFnSignature.TimerDeclaration> timer :
                  signature.timerDeclarations().entrySet()) {
                RunnerApi.TimerSpec spec =
                    translateTimerSpec(getTimerSpecOrThrow(timer.getValue(), doFn), newComponents);
                timerSpecs.put(timer.getKey(), spec);
              }
              return timerSpecs;
            }

            @Override
            public boolean isSplittable() {
              return false;
            }

            @Override
            public String translateRestrictionCoderId(SdkComponents newComponents) {
              return "";
            }
          },
          components);
    }
  }
