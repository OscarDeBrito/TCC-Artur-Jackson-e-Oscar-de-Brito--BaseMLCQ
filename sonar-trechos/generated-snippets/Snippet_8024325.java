@Singleton
public class ProjectClasspathChangeListener implements IElementChangedListener {

	private final static Logger log = Logger.getLogger(ProjectClasspathChangeListener.class);

	@Inject
	private IWorkspace workspace;
	
	@Inject
	@Deprecated
	private org.eclipse.xtext.builder.impl.BuildScheduler buildManager;
	
	@Inject 
	private IDirtyStateManager dirtyStateManager;
	
	@Inject 
	private JavaProjectClasspathChangeAnalyzer javaProjectClasspathChangeAnalyzer;

	@Override
	public void elementChanged(ElementChangedEvent event) {
		if (workspace != null && workspace.isAutoBuilding()) {
			try {
				if (event.getDelta() != null) {
					Set<IJavaProject> javaProjects = getJavaProjectsWithClasspathChange(event.getDelta());
					if (!javaProjects.isEmpty()) {
						Set<IProject> projects = FluentIterable.from(javaProjects)
								.filter(Predicates.notNull())
								.transform(IJavaProject::getProject).toSet();
						dirtyStateManager.notifyListeners(new CoarseGrainedChangeEvent());
						scheduleBuildIfNecessary(projects);
					}
				}
			} catch (WrappedException e) {
				log.error(e.getCause().getMessage(), e.getCause());
			} catch (RuntimeException e) {
				log.error(e.getMessage(), e);
			}
		}
	}

	@Deprecated
	private void scheduleBuildIfNecessary(Set<IProject> projects) {
		buildManager.scheduleBuildIfNecessary(projects, IBuildFlag.FORGET_BUILD_STATE_ONLY);
	}

	protected Set<IJavaProject> getJavaProjectsWithClasspathChange(IJavaElementDelta delta) {
		return javaProjectClasspathChangeAnalyzer.getJavaProjectsWithClasspathChange(delta);
	}

}
