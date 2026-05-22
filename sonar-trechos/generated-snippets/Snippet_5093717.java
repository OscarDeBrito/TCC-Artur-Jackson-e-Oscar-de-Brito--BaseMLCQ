public class Snippet__5093717 {

    @Override
      public void clear() {
        Arrays.fill(this.state, 0, state.length - 1, FREE);
        distinct = 0;
        freeEntries = table.length; // delta
        trimToSize();
      }

}
