public class CrunchMapper extends Mapper<Object, Object, Object, Object> {

  private static final Logger LOG = LoggerFactory.getLogger(CrunchMapper.class);

  private RTNode node;
  private CrunchTaskContext ctxt;
  private boolean debug;

  @Override
  protected void setup(Mapper<Object, Object, Object, Object>.Context context) {
    if (ctxt == null) {
      ctxt = new CrunchTaskContext(context, NodeContext.MAP);
      this.debug = ctxt.isDebugRun();
    }
    
    List<RTNode> nodes = ctxt.getNodes();
    if (nodes.size() == 1) {
      this.node = nodes.get(0);
    } else {
      CrunchInputSplit split = (CrunchInputSplit) context.getInputSplit();
      this.node = nodes.get(split.getNodeIndex());
    }
    this.node.initialize(ctxt);
  }

  @Override
  protected void map(Object k, Object v, Mapper<Object, Object, Object, Object>.Context context) {
    if (debug) {
      try {
        node.process(k, v);
      } catch (Exception e) {
        LOG.error("Mapper exception", e);
      }
    } else {
      node.process(k, v);
    }
  }

  @Override
  protected void cleanup(Mapper<Object, Object, Object, Object>.Context context) {
    node.cleanup();
    ctxt.cleanup();
  }
}
