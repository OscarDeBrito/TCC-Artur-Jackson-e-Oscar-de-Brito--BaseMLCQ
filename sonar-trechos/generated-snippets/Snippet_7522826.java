public class Snippet__7522826 {

    @Override
    	protected YarnContainer performBuild() throws Exception {
    		YarnContainerFactoryBean fb = new YarnContainerFactoryBean();
    		fb.setContainerRef(ref);
    		fb.setContainerClass(clazz);
    		fb.afterPropertiesSet();
    		YarnContainer container = fb.getObject();
    		if (container instanceof AbstractYarnContainer) {
    			((AbstractYarnContainer)container).setConfiguration(configuration);
    		}
    		return container;
    	}

}
