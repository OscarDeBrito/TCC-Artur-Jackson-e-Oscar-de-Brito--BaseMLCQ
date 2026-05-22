public class Snippet__6941861 {

    @Override
        public void bindInterceptor(
            Matcher<? super Class<?>> classMatcher,
            Matcher<? super Method> methodMatcher,
            org.aopalliance.intercept.MethodInterceptor... interceptors) {
          elements.add(
              new InterceptorBinding(getElementSource(), classMatcher, methodMatcher, interceptors));
        }

}
