public class Snippet__8580507 {

    @edu.umd.cs.findbugs.annotations.SuppressWarnings(value="NN_NAKED_NOTIFY",
            justification="This is what we want")
        public void evict() {
          synchronized (this) {
            this.notifyAll();
          }
        }

}
