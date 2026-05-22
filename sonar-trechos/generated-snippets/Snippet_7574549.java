public class Snippet__7574549 {

    @Override
    	public boolean matches(Class<?> clazz) {
    		return (this.checkInherited ? AnnotatedElementUtils.hasAnnotation(clazz, this.annotationType) :
    				clazz.isAnnotationPresent(this.annotationType));
    	}

}
