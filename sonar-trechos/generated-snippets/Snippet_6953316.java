public class Snippet__6953316 {

    @Override
    	public void eSet(int featureID, Object newValue) {
    		switch (featureID) {
    			case DomPackage.CONTENT_NODE__OWNER:
    				setOwner((Composite)newValue);
    				return;
    		}
    		super.eSet(featureID, newValue);
    	}

}
