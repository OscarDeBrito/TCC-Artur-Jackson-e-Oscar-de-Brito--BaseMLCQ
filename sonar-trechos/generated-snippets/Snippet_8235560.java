public class CocoonRunnable extends EnvironmentHelper.AbstractCocoonRunnable {
    Runnable target;

    /**
     * Creates an empty <code>CocoonRunnable</code> and copies the environment context
     * of the calling thread, for later use when calling {@link #doRun()}. Users of this
     * constructor will override the {@link #doRun()} method where the actual job gets done.
     */
    public CocoonRunnable() {
        // Nothing special here
    }

    /**
     * Wraps an existing <code>Runnable</code> and copies the environment context of
     * the calling thread, for later use when the <code>Runnable</code>'s <code>run()</code>
     * method is called.
     * 
     * @param target the wrapped <code>Runnable</code>
     */
    public CocoonRunnable(Runnable target) {
        this.target = target;
    }

    /**
     * Does the actual job, in the environment of the creating thread. Calls the wrapped
     * <code>Runnable</code> if one was given, and does nothing otherwise.
     */
    protected void doRun() {
        if (target != null) {
            target.run();
        }
    }
}
