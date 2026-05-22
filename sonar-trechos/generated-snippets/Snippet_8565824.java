public class Snippet__8565824 {

    @Override
      protected void enqueue(final Procedure procedure, final boolean addFront) {
        if (addFront) {
          runnables.addFirst(procedure);
        } else {
          runnables.addLast(procedure);
        }
      }

}
