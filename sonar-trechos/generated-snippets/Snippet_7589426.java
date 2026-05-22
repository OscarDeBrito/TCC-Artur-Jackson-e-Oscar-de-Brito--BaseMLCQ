public class Snippet__7589426 {

    public Snippet__7589426(@Nullable Object value, @Nullable Class<?> requiredType, @Nullable Throwable cause) {
    		super("Failed to convert value of type '" + ClassUtils.getDescriptiveType(value) + "'" +
    				(requiredType != null ? " to required type '" + ClassUtils.getQualifiedName(requiredType) + "'" : ""),
    				cause);
    		this.value = value;
    		this.requiredType = requiredType;
    	}

}
