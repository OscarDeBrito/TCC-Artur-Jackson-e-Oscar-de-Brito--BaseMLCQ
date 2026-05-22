public class NullNode extends AbstractProcessingNode {

    public NullNode() {
        super(null);
    }
    
    public final boolean invoke(Environment env, InvokeContext context) throws Exception {

        getLogger().warn("Invoke on NullNode at " + getLocation());
        return false;

    }
}
