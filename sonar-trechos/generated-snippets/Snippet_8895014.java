public class Snippet__8895014 {

    public void addToAborted(long elem) {
        if (this.aborted == null) {
          this.aborted = new HashSet<Long>();
        }
        this.aborted.add(elem);
      }

}
