public class Snippet__8051170 {

    private String getRawReturnType(JvmOperation singleOperation) {
    		JvmTypeReference returnType = singleOperation.getReturnType();
    		if (returnType == null)
    			return null;
    		JvmType rawType = returnType.getType();
    		if (rawType == null) {
    			return null;
    		}
    		return rawType.getQualifiedName();
    	}

}
