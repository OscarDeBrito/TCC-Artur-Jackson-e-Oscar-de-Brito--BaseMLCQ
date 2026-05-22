public class Snippet__4758737 {

    private static Method getMethod(String className, String methodName, Class<?>... parameterTypes)
          throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName(className);
        return clazz.getMethod(methodName, parameterTypes);
      }

}
