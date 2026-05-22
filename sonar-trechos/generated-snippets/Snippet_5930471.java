public class Snippet__5930471 {

    public void threadSingletonServiceAdded(@Observes ComponentAdded<ThreadSingletonService> componentAdded) {
            if (componentAdded.getComponent() != this) {
                return;
            }

            contextThreadListener = new OWBContextThreadListener();
            ThreadContext.addThreadContextListener(contextThreadListener);
        }

}
