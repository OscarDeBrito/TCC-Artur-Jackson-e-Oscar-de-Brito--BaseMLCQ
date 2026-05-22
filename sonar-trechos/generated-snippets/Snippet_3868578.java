public class ResolveAllHandler extends AbstractHandler {

    public Object execute(ExecutionEvent event) throws ExecutionException {
        IJavaModel model = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot());
        IJavaProject[] projects;
        try {
            projects = model.getJavaProjects();
        } catch (JavaModelException e) {
            // TODO deal with it properly
            return null;
        }

        for (IJavaProject project : projects) {
            for (IvyClasspathContainer container : IvyClasspathContainerHelper.getContainers(project)) {
                container.launchResolve(false, null);
            }
        }

        return null;
    }

}
