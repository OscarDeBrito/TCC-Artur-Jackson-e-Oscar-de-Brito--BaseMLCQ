public class Snippet__5048033 {

    private static Var asVar(Node node)
        {
            if ( Var.isVar(node) )
                return Var.alloc(node) ;
            return null ;
        }

}
