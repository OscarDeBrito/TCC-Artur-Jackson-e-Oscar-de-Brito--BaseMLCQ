public class Snippet__8850497 {

    @Override
        public int hashCode() {
          List<Object> list = new ArrayList<Object>();

          boolean present_query = true && (isSetQuery());
          list.add(present_query);
          if (present_query)
            list.add(query);

          return list.hashCode();
        }

}
