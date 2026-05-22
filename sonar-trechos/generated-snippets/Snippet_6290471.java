public class Snippet__6290471 {

    public void setAsyncWriteTimeout(long ms)
        {
            assertLessThan("AsyncWriteTimeout",ms,"IdleTimeout",idleTimeout);
            this.asyncWriteTimeout = ms;
        }

}
