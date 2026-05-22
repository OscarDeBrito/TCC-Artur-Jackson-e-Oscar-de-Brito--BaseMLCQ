public class Snippet__4198515 {

    static final class PathAccessor implements Accessor {

    		private static final long serialVersionUID = 2056090443413498626L;

    		private final String segmentName;
    		private final Accessor nextAccessor;

    		public PathAccessor(String segmentName, Accessor nextAccessor) {
    			// trim outer join component
    			if(segmentName.endsWith(Entity.OUTER_JOIN_INDICATOR)) {
    				this.segmentName = segmentName.substring(0, segmentName.length() - 1);
    			} else {
    				this.segmentName = segmentName;
    			}
    			this.nextAccessor = nextAccessor;
    		}

    		@Override
    		public String getName() {
    			return segmentName;
    		}

    		@Override
    		public Object getValue(Object object) throws PropertyException {
    			if (object == null) {
    				return null;
    			}

    			Object value = getOrCreateSegmentAccessor(object.getClass(), segmentName).getValue(object);
    			return nextAccessor != null ? nextAccessor.getValue(value) : value;
    		}

    		@Override
    		public void setValue(Object object, Object newValue) throws PropertyException {
    			if (object == null) {
    				return;
    			}

    			Accessor segmentAccessor = getOrCreateSegmentAccessor(object.getClass(), segmentName);
    			if (nextAccessor != null) {
    				nextAccessor.setValue(segmentAccessor.getValue(object), newValue);
    			} else {
    				segmentAccessor.setValue(object, newValue);
    			}
    		}
    	}

}
