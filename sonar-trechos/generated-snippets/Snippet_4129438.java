public class Snippet__4129438 {

    abstract static class ElasticsearchConverterRule extends ConverterRule {
        final Convention out;

        ElasticsearchConverterRule(Class<? extends RelNode> clazz, RelTrait in, Convention out,
            String description) {
          super(clazz, in, out, description);
          this.out = out;
        }
      }

}
