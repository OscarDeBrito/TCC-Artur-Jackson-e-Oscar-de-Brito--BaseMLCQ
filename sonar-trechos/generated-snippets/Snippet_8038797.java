public class Snippet__8038797 {

    public static Iterator<EObject> getNonDerivedContents(EObject eObject) {
    		EClassImpl.FeatureSubsetSupplier featureSupplier = (EClassImpl.FeatureSubsetSupplier) eObject.eClass().getEAllStructuralFeatures();
    		EStructuralFeature[] eStructuralFeatures = featureSupplier.containments();

    		return (eStructuralFeatures == null 
    				? EContentsEList.<EObject> emptyContentsEList()
    				: new EContentsEList<EObject>(eObject, eStructuralFeatures) {
    					@Override
    					protected ListIterator<EObject> newResolvingListIterator() {
    						return new ResolvingFeatureIteratorImpl<EObject>(eObject, eStructuralFeatures) {
    							@Override
    							protected boolean isIncluded(EStructuralFeature eStructuralFeature) {
    								return !eStructuralFeature.isDerived();
    							}
    						};
    					}
    				}).iterator();
    	}

}
