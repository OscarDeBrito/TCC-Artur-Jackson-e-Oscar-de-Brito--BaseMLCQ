public class Snippet__7551760 {

    @Override
    	public <T> T getValue(Class<T> desiredResultType) throws EvaluationException {
    		return org.springframework.expression.common.ExpressionUtils
    				.convertTypedValue(null, this.typedResultValue, desiredResultType);
    	}

}
