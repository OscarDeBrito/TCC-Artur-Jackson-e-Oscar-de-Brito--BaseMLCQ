public class Snippet__9096801 {

    protected static final class GenericNumericVectorNode extends TruffleBoundaryNode {

            @Child private UnaryMapNode cached;

            public UnaryMapNode get(UnaryArithmetic arithmetic, RAbstractVector operand) {
                UnaryMapNode map = cached;
                if (map == null || !map.isSupported(operand)) {
                    cached = map = insert(createCached(arithmetic, operand, true));
                }
                return map;
            }
        }

}
