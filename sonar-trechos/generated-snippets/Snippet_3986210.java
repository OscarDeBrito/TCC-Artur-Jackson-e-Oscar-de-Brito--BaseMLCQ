public class Snippet__3986210 {

    public static Class<?> createProxyClass(ClassLoader mainTypeClassLoader, Class<?>[] interfaces) {
            ClassLoader effectiveClassLoader = Thread.currentThread().getContextClassLoader();
            if (effectiveClassLoader == null) {
                effectiveClassLoader = mainTypeClassLoader;
            }
            return Proxy.getProxyClass(effectiveClassLoader, interfaces);
        }

}
