public class BatchlogResponseHandler<T> extends AbstractWriteResponseHandler<T>
{
    AbstractWriteResponseHandler<T> wrapped;
    BatchlogCleanup cleanup;
    protected volatile int requiredBeforeFinish;
    private static final AtomicIntegerFieldUpdater<BatchlogResponseHandler> requiredBeforeFinishUpdater
            = AtomicIntegerFieldUpdater.newUpdater(BatchlogResponseHandler.class, "requiredBeforeFinish");

    public BatchlogResponseHandler(AbstractWriteResponseHandler<T> wrapped, int requiredBeforeFinish, BatchlogCleanup cleanup, long queryStartNanoTime)
    {
        super(wrapped.replicaPlan, wrapped.callback, wrapped.writeType, queryStartNanoTime);
        this.wrapped = wrapped;
        this.requiredBeforeFinish = requiredBeforeFinish;
        this.cleanup = cleanup;
    }

    protected int ackCount()
    {
        return wrapped.ackCount();
    }

    public void response(MessageIn<T> msg)
    {
        wrapped.response(msg);
        if (requiredBeforeFinishUpdater.decrementAndGet(this) == 0)
            cleanup.ackMutation();
    }

    public boolean isLatencyForSnitch()
    {
        return wrapped.isLatencyForSnitch();
    }

    public void onFailure(InetAddressAndPort from, RequestFailureReason failureReason)
    {
        wrapped.onFailure(from, failureReason);
    }

    public void get() throws WriteTimeoutException, WriteFailureException
    {
        wrapped.get();
    }

    protected int blockFor()
    {
        return wrapped.blockFor();
    }

    protected int candidateReplicaCount()
    {
        return wrapped.candidateReplicaCount();
    }

    protected boolean waitingFor(InetAddressAndPort from)
    {
        return wrapped.waitingFor(from);
    }

    protected void signal()
    {
        wrapped.signal();
    }

    public static class BatchlogCleanup
    {
        private final BatchlogCleanupCallback callback;

        protected volatile int mutationsWaitingFor;
        private static final AtomicIntegerFieldUpdater<BatchlogCleanup> mutationsWaitingForUpdater
            = AtomicIntegerFieldUpdater.newUpdater(BatchlogCleanup.class, "mutationsWaitingFor");

        public BatchlogCleanup(int mutationsWaitingFor, BatchlogCleanupCallback callback)
        {
            this.mutationsWaitingFor = mutationsWaitingFor;
            this.callback = callback;
        }

        public void ackMutation()
        {
            if (mutationsWaitingForUpdater.decrementAndGet(this) == 0)
                callback.invoke();
        }
    }

    public interface BatchlogCleanupCallback
    {
        void invoke();
    }
}
