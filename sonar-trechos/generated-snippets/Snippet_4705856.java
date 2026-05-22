public class Snippet__4705856 {

    protected void execute(final IOEventHandlerFactory ioEventHandlerFactory) throws IOException {
            Args.notNull(ioEventHandlerFactory, "Handler factory");
            if (ioReactorRef.compareAndSet(null, createIOReactor(
                    ioEventHandlerFactory,
                    ioReactorConfig,
                    workerThreadFactory,
                    ShutdownCommand.GRACEFUL_NORMAL_CALLBACK))) {
                if (status.compareAndSet(Status.READY, Status.RUNNING)) {
                    ioReactorRef.get().start();
                }
            } else {
                throw new IllegalStateException("I/O reactor has already been started");
            }
        }

}
