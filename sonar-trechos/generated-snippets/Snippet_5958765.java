public class Snippet__5958765 {

    @Managed
        public void flush() {
            stats.flushes.record();
            poolVersion.incrementAndGet();
        }

}
