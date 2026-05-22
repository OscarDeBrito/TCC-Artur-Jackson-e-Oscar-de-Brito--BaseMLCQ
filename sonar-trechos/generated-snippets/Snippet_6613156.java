public interface Procedure<F> extends Function<F, Void> {

	@Override
	default Void apply(F input) {
		doApply(input);
		return null;
	}

	/**
	 * Applies the procedure on the input argument.
	 *
	 * @param input
	 *            the input
	 */
	void doApply(final F input);

}
