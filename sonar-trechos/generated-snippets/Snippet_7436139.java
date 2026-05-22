public class Snippet__7436139 {

    private static void registerUnaryMath(Registration r, String name, UnaryOperation operation) {
            r.register1(name, Double.TYPE, new InvocationPlugin() {
                @Override
                public boolean apply(GraphBuilderContext b, ResolvedJavaMethod targetMethod, Receiver receiver, ValueNode value) {
                    b.push(JavaKind.Double, b.append(UnaryMathIntrinsicNode.create(value, operation)));
                    return true;
                }
            });
        }

}
