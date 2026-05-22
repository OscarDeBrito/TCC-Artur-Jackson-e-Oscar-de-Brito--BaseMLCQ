public class Snippet__8999846 {

    @Override
        public void streamObjects(Consumer<Object> cons) {
          Object val = getObject();
          if (exists()) {
            cons.accept(val);
          }
        }

}
