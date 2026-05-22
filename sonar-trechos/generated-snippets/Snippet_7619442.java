public class Snippet__7619442 {

    @Override
    		public final void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
    			try {
    				doit(monitor);
    			} catch (InterruptedException e) {
    				throw e;
    			} catch (OperationCanceledException e) {
    				throw new InterruptedException("Canceled by user");
    			} catch (InvocationTargetException e) {
    				throw e;
    			} catch (Throwable e) {
    				throw new InvocationTargetException(e);
    			}
    		}

}
