public class Snippet__8907524 {

    @Override public boolean nextKeyValue() {
        if (values.hasNext()) {
          return true;
        }
        if (queryResultsIterator.hasNext()) {
          current = queryResultsIterator.next();
          values = current.getValue().getValue().iterator();
          return nextKeyValue();
        }
        return false;
      }

}
