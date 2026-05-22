public class Snippet__4330910 {

    @Override
        public void close() throws IOException
        {
            if ( IS_DEBUG )
            {
                LOG_CURSOR.debug( "Closing LessEqCursor {}", this );
            }

            super.close();

            if ( userIdxCursor != null )
            {
                userIdxCursor.close();
            }
            else
            {
                uuidIdxCursor.close();
                uuidCandidate = null;
            }
        }

}
