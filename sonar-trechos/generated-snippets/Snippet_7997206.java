public class Snippet__7997206 {

    protected void appendDefaultLiteral(ITreeAppendable b, /* @Nullable */ LightweightTypeReference type) {
    		if (type != null && type.isPrimitive()) {
    			Primitive primitiveKind = type.getPrimitiveKind();
    			switch (primitiveKind) {
    				case Boolean:
    					b.append("false");
    					break;
    				default:
    					b.append("0");
    					break;
    			}
    		} else {
    			b.append("null");
    		}
    	}

}
