public class Snippet__6889571 {

    @Override
    	public void eUnset(int featureID) {
    		switch (featureID) {
    			case TypesPackage.TCONSTABLE_ELEMENT__CONST:
    				setConst(CONST_EDEFAULT);
    				return;
    			case TypesPackage.TCONSTABLE_ELEMENT__COMPILE_TIME_VALUE:
    				setCompileTimeValue(COMPILE_TIME_VALUE_EDEFAULT);
    				return;
    		}
    		super.eUnset(featureID);
    	}

}
