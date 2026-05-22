public class Snippet__5657989 {

    protected void assertNotClosed() throws SQLConnectionClosedException {
            this.statement.assertNotClosed();

            if (this.isClosed) {
                throw new SQLConnectionClosedException(format(Level.WARNING, "excp.closed_resultset", this.sql, getFile().getName()), this.sql, getFile());
            }
        }

}
