public class Snippet__8052959 {

    @Override
    		public IScope getScope(EObject context, EReference reference) {
    			if (reference == SequencertestPackage.Literals.NULL_CROSS_REF__REF)
    				return new AbstractScope(IScope.NULLSCOPE, false) {

    					@Override
    					public Iterable<IEObjectDescription> getElements(EObject object) {
    						return Collections.singletonList(getSingleElement(object));
    					}

    					@Override
    					public IEObjectDescription getSingleElement(EObject object) {
    						return EObjectDescription.create("null", null);
    					}

    					@Override
    					protected Iterable<IEObjectDescription> getAllLocalElements() {
    						throw new UnsupportedOperationException();
    					}
    				};
    			return super.getScope(context, reference);
    		}

}
