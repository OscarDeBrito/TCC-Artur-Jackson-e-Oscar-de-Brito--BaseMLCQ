public class Snippet__7377189 {

    @SuppressWarnings("unchecked")
    	private static <T> boolean test(Annotation annotation, String metaAnnotationName, String attribute,
    			Predicate<T> filter) {

    		if (annotation.annotationType().getName().equals(metaAnnotationName)) {

    			Map<String, Object> attributes = AnnotationUtils.getAnnotationAttributes(annotation);

    			return !attributes.isEmpty() && filter.test((T) attributes.get(attribute));
    		}

    		MultiValueMap<String, Object> attributes = AnnotatedElementUtils
    				.getAllAnnotationAttributes(annotation.annotationType(), metaAnnotationName);

    		if (attributes == null || attributes.isEmpty()) {
    			return false;
    		}

    		List<Object> elementTypes = attributes.get(attribute);

    		for (Object value : elementTypes) {

    			if (filter.test((T) value)) {
    				return true;
    			}
    		}
    		return false;
    	}

}
