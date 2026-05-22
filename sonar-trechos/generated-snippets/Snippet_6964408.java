public class Snippet__6964408 {

    @Override
        public void forEach(Consumer<? super E> action) {
          synchronized (mutex) {
            delegate().forEach(action);
          }
        }

}
