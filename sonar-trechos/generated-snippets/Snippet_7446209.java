public class JpaParametersParameterAccessor extends ParametersParameterAccessor {

	/**
	 * Creates a new {@link ParametersParameterAccessor}.
	 *
	 * @param parameters must not be {@literal null}.
	 * @param values must not be {@literal null}.
	 */
	JpaParametersParameterAccessor(Parameters<?, ?> parameters, Object[] values) {
		super(parameters, values);
	}

	public <T> T getValue(Parameter parameter) {
		return super.getValue(parameter.getIndex());
	}
}
