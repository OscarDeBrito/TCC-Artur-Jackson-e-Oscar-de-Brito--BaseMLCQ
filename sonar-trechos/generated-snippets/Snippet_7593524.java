public class Snippet__7593524 {

    @Nullable
    	public Interceptor getEntityInterceptor() throws IllegalStateException, BeansException {
    		if (this.entityInterceptor instanceof Interceptor) {
    			return (Interceptor) this.entityInterceptor;
    		}
    		else if (this.entityInterceptor instanceof String) {
    			if (this.beanFactory == null) {
    				throw new IllegalStateException("Cannot get entity interceptor via bean name if no bean factory set");
    			}
    			String beanName = (String) this.entityInterceptor;
    			return this.beanFactory.getBean(beanName, Interceptor.class);
    		}
    		else {
    			return null;
    		}
    	}

}
