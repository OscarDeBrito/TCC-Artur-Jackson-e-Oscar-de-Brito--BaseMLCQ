public class Snippet__7568142 {

    @Override
    	protected boolean includeOperation(Method method, String beanKey) {
    		PropertyDescriptor pd = BeanUtils.findPropertyForMethod(method);
    		return (pd != null && hasManagedAttribute(method)) || hasManagedOperation(method);
    	}

}
