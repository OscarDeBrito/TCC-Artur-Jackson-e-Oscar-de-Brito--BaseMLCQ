public class Snippet__6034374 {

    @Override
    		protected IStatus run(IProgressMonitor monitor) {
    			try {
    				cloudServer.getBehaviour().connect(monitor);
				
    				return Status.OK_STATUS;
				
    			} catch (CoreException e) {
    				e.printStackTrace();
    				return e.getStatus();
    			}
			
    		}

}
