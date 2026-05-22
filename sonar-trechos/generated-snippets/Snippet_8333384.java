public class Snippet__8333384 {

    public final Thread start() {
    			if (!init) {
    				throw new IllegalStateException("not initialized");
    			}
    			synchronized (this) {
    				if (started) {
    					throw new IllegalStateException("already started");
    				}
    				started = true;
    			}
    			try {
    				process = Runtime.getRuntime().exec(command);
    			} catch (IOException e) {
    				stop(e, Integer.MIN_VALUE);
    				return null;
    			}
    			errStream = new FileUtil.Pipe(process.getErrorStream(), System.err);
    			if (null != errSnoop) {
    				errStream.setSnoop(errSnoop);
    			}
    			outStream = new FileUtil.Pipe(process.getInputStream(), System.out);
    			if (null != outSnoop) {
    				outStream.setSnoop(outSnoop);
    			}
    			inStream = new FileUtil.Pipe(System.in, process.getOutputStream());
    			// start 4 threads, process & pipes for in, err, out
    			Runnable processRunner = new Runnable() {
    				@Override
    				public void run() {
    					Throwable thrown = null;
    					int result = Integer.MIN_VALUE;
    					try {
    						// pipe threads are children
    						new Thread(errStream).start();
    						new Thread(outStream).start();
    						new Thread(inStream).start();
    						process.waitFor();
    						result = process.exitValue();
    					} catch (Throwable e) {
    						thrown = e;
    					} finally {
    						stop(thrown, result);
    					}
    				}
    			};
    			Thread result = new Thread(processRunner, label);
    			result.start();
    			return result;
    		}

}
