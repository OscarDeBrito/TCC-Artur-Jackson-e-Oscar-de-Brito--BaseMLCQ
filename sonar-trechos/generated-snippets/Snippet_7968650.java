public class XbaseWithAnnotationsExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return Platform.getBundle(XbaseActivator.PLUGIN_ID);
	}
	
	@Override
	protected Injector getInjector() {
		XbaseActivator activator = XbaseActivator.getInstance();
		return activator != null ? activator.getInjector(XbaseActivator.ORG_ECLIPSE_XTEXT_XBASE_ANNOTATIONS_XBASEWITHANNOTATIONS) : null;
	}

}
