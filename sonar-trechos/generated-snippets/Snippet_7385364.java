public class Snippet__7385364 {

    private int readOrder(AnnotationMetadata annotationMetadata) {
    			Map<String, Object> attributes = annotationMetadata
    					.getAnnotationAttributes(Order.class.getName());
    			Integer order = (attributes != null) ? (Integer) attributes.get("value")
    					: null;
    			return (order != null) ? order : Ordered.LOWEST_PRECEDENCE;
    		}

}
