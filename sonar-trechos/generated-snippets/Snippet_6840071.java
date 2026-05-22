public class Snippet__6840071 {

    protected void sequence_JSXSpreadAttribute(ISerializationContext context, JSXSpreadAttribute semanticObject) {
    		if (errorAcceptor != null) {
    			if (transientValues.isValueTransient(semanticObject, N4JSPackage.Literals.JSX_SPREAD_ATTRIBUTE__EXPRESSION) == ValueTransient.YES)
    				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, N4JSPackage.Literals.JSX_SPREAD_ATTRIBUTE__EXPRESSION));
    		}
    		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
    		feeder.accept(grammarAccess.getJSXSpreadAttributeAccess().getExpressionAssignmentExpressionParserRuleCall_2_0(), semanticObject.getExpression());
    		feeder.finish();
    	}

}
