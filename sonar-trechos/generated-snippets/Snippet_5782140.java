public class Snippet__5782140 {

    @Override
        public void onVertexManagerEventReceived(VertexManagerEvent vmEvent)
            throws Exception {
          if (LOG.isDebugEnabled()) {
            LOG.debug("onVertexManagerEventReceived is invoked in NoOpVertexManager, vertex=" + getContext().getVertexName());
          }
        }

}
