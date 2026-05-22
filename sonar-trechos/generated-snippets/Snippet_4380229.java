public class Snippet__4380229 {

    public static boolean accept(AST node) {
            return TypedefVisitor.accept(node)
                || StructVisitor.accept(node)
                || UnionVisitor.accept(node)
                || EnumVisitor.accept(node);
        }

}
