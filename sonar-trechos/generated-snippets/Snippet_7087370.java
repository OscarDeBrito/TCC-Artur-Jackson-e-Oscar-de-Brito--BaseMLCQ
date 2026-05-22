public class Snippet__7087370 {

    public int getRoot()
      {
      	if(null != m_iter)
        	return m_iter.getRoot();
      	else
      	{
      		// NodeSetDTM will call this, and so it's not a good thing to throw 
      		// an assertion here.
      		// assertion(false, "Can not get the root from a non-iterated NodeSequence!");
      		return DTM.NULL;
      	}
      }

}
