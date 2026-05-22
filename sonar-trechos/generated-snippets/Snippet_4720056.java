public class Snippet__4720056 {

    @Override
        public void generateGroovy(GroovyGenerationContext context) {
            context.append(label);
            context.append(":");
            expr.generateGroovy(context);
        }

}
