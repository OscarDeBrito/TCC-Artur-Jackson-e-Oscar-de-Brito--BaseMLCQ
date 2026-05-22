public class Snippet__5780746 {

    @Private
      public static <T> T createClazzInstance(String className) throws TezReflectionException {
        Class<?> clazz = getClazz(className);
        @SuppressWarnings("unchecked")
        T instance = (T) getNewInstance(clazz);
        return instance;
      }

}
