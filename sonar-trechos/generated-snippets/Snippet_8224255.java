public class Snippet__8224255 {

    final public class AvalonServiceManager
        implements ServiceManager, BeanFactoryAware {

        /** The bean factory this service manager is defined in. */
        protected BeanFactory beanFactory;

        /**
         * @see org.springframework.beans.factory.BeanFactoryAware#setBeanFactory(org.springframework.beans.factory.BeanFactory)
         */
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            this.beanFactory = beanFactory;
        }

        /**
         * @see org.apache.avalon.framework.service.ServiceManager#hasService(java.lang.String)
         */
        public boolean hasService(String role) {
            return this.beanFactory.containsBean(role);
        }

        /**
         * @see org.apache.avalon.framework.service.ServiceManager#lookup(java.lang.String)
         */
        public Object lookup(String role) throws ServiceException {
            if ( !this.hasService(role) ) {
                throw new ServiceException("AvalonServiceManager",
                                           "Component with '" + role + "' is not defined in this service manager.");
            }
            try {
                return this.beanFactory.getBean(role);
            } catch (BeansException be) {
                throw new ServiceException("AvalonServiceManager",
                                           "Exception during lookup of component with '" + role + "'.", be);
            }
        }

        /**
         * @see org.apache.avalon.framework.service.ServiceManager#release(java.lang.Object)
         */
        public void release(Object component) {
            if ( component instanceof AvalonPoolable ) {
                ((AvalonPoolable)component).putBackIntoAvalonPool();
            }
        }
    }

}
