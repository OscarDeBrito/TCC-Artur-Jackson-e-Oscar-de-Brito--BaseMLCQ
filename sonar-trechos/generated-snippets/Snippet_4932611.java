public class Snippet__4932611 {

    public WikiPage setRealPage( WikiPage page )
        {
            WikiPage old = m_realPage;
            m_realPage = page;
            updateCommand( m_command.getRequestContext() );
            return old;
        }

}
