public class Snippet__8049248 {

    protected void sequence_Fragment1_FragmentCaller(ISerializationContext context, FragmentCallerType semanticObject) {
    		if (errorAcceptor != null) {
    			if (transientValues.isValueTransient(semanticObject, SequencertestPackage.Literals.FRAGMENT_CALLER_TYPE__VAL1) == ValueTransient.YES)
    				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SequencertestPackage.Literals.FRAGMENT_CALLER_TYPE__VAL1));
    			if (transientValues.isValueTransient(semanticObject, SequencertestPackage.Literals.FRAGMENT_CALLER_TYPE__FRAG_VAL) == ValueTransient.YES)
    				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SequencertestPackage.Literals.FRAGMENT_CALLER_TYPE__FRAG_VAL));
    			if (transientValues.isValueTransient(semanticObject, SequencertestPackage.Literals.FRAGMENT_CALLER_TYPE__VAL) == ValueTransient.YES)
    				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SequencertestPackage.Literals.FRAGMENT_CALLER_TYPE__VAL));
    		}
    		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
    		feeder.accept(grammarAccess.getFragmentCallerAccess().getVal1IDTerminalRuleCall_1_0(), semanticObject.getVal1());
    		feeder.accept(grammarAccess.getFragment1Access().getFragValIDTerminalRuleCall_0(), semanticObject.getFragVal());
    		feeder.accept(grammarAccess.getFragmentCallerAccess().getValIDTerminalRuleCall_3_0(), semanticObject.getVal());
    		feeder.finish();
    	}

}
