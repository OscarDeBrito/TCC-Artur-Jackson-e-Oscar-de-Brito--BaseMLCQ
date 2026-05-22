public abstract class AbstractPluginProjectCreator extends AbstractProjectCreator {

	@Inject
	private Provider<PluginProjectFactory> projectFactoryProvider;
	
	@Override
	protected ProjectFactory configureProjectFactory(ProjectFactory factory) {
		PluginProjectFactory result = (PluginProjectFactory) super.configureProjectFactory(factory);
		
		result.addRequiredBundles(getRequiredBundles());
		result.addExportedPackages(getExportedPackages());
		result.addImportedPackages(getImportedPackages());
		result.setActivatorClassName(getActivatorClassName());
		
		return result;
	}
	
	@Override
	protected PluginProjectFactory createProjectFactory() {
		return projectFactoryProvider.get();
	}
	
	/**
     * @return the names of the exported packages. May not be <code>null</code>
     */
	protected List<String> getExportedPackages() {
        return Collections.emptyList();
    }

	/**
     * @return the names of the imported packages that a new project requires. May not be <code>null</code>
     */
    protected List<String> getImportedPackages() {
        return Lists.newArrayList(
        		"org.apache.log4j", 
        		"org.apache.commons.logging");
    }

    /**
     * @return the class-name of the bundle-activator or <code>null</code>
     */
    protected String getActivatorClassName() {
        return null;
    }
	
    /**
     * @return the names of the bundles that a new project requires. May not be <code>null</code>
     */
	protected List<String> getRequiredBundles() {
		return Lists.newArrayList(
			"com.ibm.icu",
			"org.eclipse.xtext", 
			"org.eclipse.xtext.generator",
			"org.eclipse.xtend",
			"org.eclipse.xtend.typesystem.emf",
			"org.eclipse.xpand", 
			"de.itemis.xtext.antlr;resolution:=optional",
			"org.eclipse.emf.mwe2.launch;resolution:=optional");
	}

	@Override
	protected String[] getProjectNatures() {
		return new String[] {
			JavaCore.NATURE_ID,
			"org.eclipse.pde.PluginNature", //$NON-NLS-1$
			XtextProjectHelper.NATURE_ID
		};
	}

	@Override
	protected String[] getBuilders() {
		return new String[]{
			JavaCore.BUILDER_ID,
			"org.eclipse.pde.ManifestBuilder",  //$NON-NLS-1$
			"org.eclipse.pde.SchemaBuilder", //$NON-NLS-1$
			XtextProjectHelper.BUILDER_ID
		};
	}

}
