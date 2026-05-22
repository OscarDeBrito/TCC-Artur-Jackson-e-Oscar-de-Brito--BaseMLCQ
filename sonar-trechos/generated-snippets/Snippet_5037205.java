public class Binding0 extends BindingBase
{
    /* package */ Binding0() { super(null) ; }
    /* package */ Binding0(Binding parent) { super(parent) ; }

    /** Iterate over all the names of variables.
     */
    @Override
    public Iterator<Var> vars1() { return Iter.nullIterator() ; }

    @Override
    protected int size1() { return 0 ; }
    
    @Override
    protected boolean isEmpty1() { return true ; }
    
    @Override
    public boolean contains1(Var var) { return false ; }
    
    @Override
    public Node get1(Var var) { return null ; }
}
