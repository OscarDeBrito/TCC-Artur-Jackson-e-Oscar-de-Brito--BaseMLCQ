public final class InternalIterableProcessWindowFunction<IN, OUT, KEY, W extends Window>
		extends WrappingFunction<ProcessWindowFunction<IN, OUT, KEY, W>>
		implements InternalWindowFunction<Iterable<IN>, OUT, KEY, W> {

	private static final long serialVersionUID = 1L;

	private final InternalProcessWindowContext<IN, OUT, KEY, W> ctx;

	public InternalIterableProcessWindowFunction(ProcessWindowFunction<IN, OUT, KEY, W> wrappedFunction) {
		super(wrappedFunction);
		this.ctx = new InternalProcessWindowContext<>(wrappedFunction);
	}

	@Override
	public void process(KEY key, final W window, final InternalWindowContext context, Iterable<IN> input, Collector<OUT> out) throws Exception {
		this.ctx.window = window;
		this.ctx.internalContext = context;
		ProcessWindowFunction<IN, OUT, KEY, W> wrappedFunction = this.wrappedFunction;
		wrappedFunction.process(key, ctx, input, out);
	}

	@Override
	public void clear(final W window, final InternalWindowContext context) throws Exception {
		this.ctx.window = window;
		this.ctx.internalContext = context;
		ProcessWindowFunction<IN, OUT, KEY, W> wrappedFunction = this.wrappedFunction;
		wrappedFunction.clear(ctx);
	}

	@Override
	public RuntimeContext getRuntimeContext() {
		throw new RuntimeException("This should never be called.");
	}

	@Override
	public IterationRuntimeContext getIterationRuntimeContext() {
		throw new RuntimeException("This should never be called.");
	}
}
