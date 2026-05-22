public class Snippet__7621803 {

    protected IProject getProject(Object obj) {
    		IProject project = null;
    		if (obj instanceof IProject) {
    			project = (IProject) obj;
    		} else if (obj instanceof IJavaProject) {
    			project = ((IJavaProject) obj).getProject();
    		} else if (obj instanceof IAdaptable) {
    			project = (IProject) ((IAdaptable) obj).getAdapter(IProject.class);
    		} else if (obj instanceof BootDashElement) {
    			project = ((BootDashElement) obj).getProject();
    		}
    		return project;
    	}

}
