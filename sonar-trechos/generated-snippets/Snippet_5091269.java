public class Snippet__5091269 {

    protected void configure(boolean noOutput, TreeBuilder treeBuilder, Dataset dataset) {
        Preconditions.checkArgument(treeBuilder != null, "TreeBuilder not found in the Job parameters");
        this.noOutput = noOutput;
        this.treeBuilder = treeBuilder;
        this.dataset = dataset;
      }

}
