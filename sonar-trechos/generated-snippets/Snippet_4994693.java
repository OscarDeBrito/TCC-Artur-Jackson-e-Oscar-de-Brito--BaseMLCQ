public class RunScriptOnNodeAndAddToGoodMapOrPutExceptionIntoBadMap implements Callable<ExecResponse> {

   @Resource
   @Named(ComputeServiceConstants.COMPUTE_LOGGER)
   protected Logger logger = Logger.NULL;
   private final RunScriptOnNode runScriptOnNode;
   private final Map<NodeMetadata, Exception> badNodes;
   private final Map<NodeMetadata, ExecResponse> goodNodes;

   private transient boolean tainted;

   @AssistedInject
   public RunScriptOnNodeAndAddToGoodMapOrPutExceptionIntoBadMap(RunScriptOnNode runScriptOnNode,
            Map<NodeMetadata, ExecResponse> goodNodes, Map<NodeMetadata, Exception> badNodes) {
      this.runScriptOnNode = checkNotNull(runScriptOnNode, "runScriptOnNode");
      this.badNodes = checkNotNull(badNodes, "badNodes");
      this.goodNodes = checkNotNull(goodNodes, "goodNodes");
   }

   @Override
   public ExecResponse call() {
      checkState(runScriptOnNode != null, "runScriptOnNode must be set");
      checkState(!tainted, "this object is not designed to be reused: %s", toString());
      tainted = true;
      try {
         ExecResponse exec = runScriptOnNode.call();
         logger.debug("<< options applied node(%s)", runScriptOnNode.getNode().getId());
         logger.trace("<< script output for node(%s): %s", runScriptOnNode.getNode().getId(), exec);
         goodNodes.put(runScriptOnNode.getNode(), exec);
         return exec;
      } catch (Exception e) {
         logger.error(e, "<< problem applying options to node(%s): ", runScriptOnNode.getNode().getId(),
                  getRootCause(e).getMessage());
         badNodes.put(runScriptOnNode.getNode(), e);
      }
      return null;
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this).add("runScriptOnNode", runScriptOnNode).add("goodNodes", goodNodes).add(
               "badNodes", badNodes).toString();
   }

}
