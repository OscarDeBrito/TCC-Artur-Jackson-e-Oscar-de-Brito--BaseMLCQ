public class Snippet__4665134 {

    @Override
    	public <T> T invokeAny(
    		@Nonnull Collection<? extends Callable<T>> tasks,
    		long timeout,
    		@Nonnull TimeUnit unit) throws ExecutionException, TimeoutException {

    		long end = System.currentTimeMillis() + unit.toMillis(timeout);
    		Exception exception = null;

    		Iterator<? extends Callable<T>> iterator = tasks.iterator();

    		while (end > System.currentTimeMillis() && iterator.hasNext()) {
    			Callable<T> callable = iterator.next();

    			try {
    				return callable.call();
    			} catch (Exception e) {
    				// ignore exception and try next
    				exception = e;
    			}
    		}

    		if (iterator.hasNext()) {
    			throw new TimeoutException("Could not finish execution of tasks within time.");
    		} else {
    			throw new ExecutionException("No tasks finished successfully.", exception);
    		}
    	}

}
