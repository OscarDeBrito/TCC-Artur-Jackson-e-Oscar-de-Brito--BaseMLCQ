public class Snippet__7416098 {

    private void destroyBeanFactoryLocator() {
    		Optional.ofNullable(getBeanFactoryLocator()).ifPresent(GemfireBeanFactoryLocator::destroy);
    		this.beanFactoryLocator = null;
    	}

}
