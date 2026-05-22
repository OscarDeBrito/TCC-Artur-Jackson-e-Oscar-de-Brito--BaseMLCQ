public class Snippet__8574638 {

    @SuppressWarnings("unchecked")
      public E loadInstance(Class<?> implClass) throws IOException {
        // create the instance
        E impl;
        Object o = null;
        try {
          o = implClass.getDeclaredConstructor().newInstance();
          impl = (E)o;
        } catch (Exception e) {
          throw new IOException(e);
        }

        return impl;
      }

}
