public class Snippet__7627967 {

    @SuppressWarnings("unchecked")
    	public static <T> T getParentOfClass(IModelElement child, Class<T> parentType) {
    		if (child != null) {
    			IModelElement parent = child.getElementParent();
    			while (parent != null) {
    				if (parentType.isAssignableFrom(parent.getClass())) {
    					return (T) parent;
    				}
    				parent = parent.getElementParent();
    			}
    		}
    		return null;
    	}

}
