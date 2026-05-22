public class Snippet__8649493 {

    private abstract static class GridifyMethodMatcher implements MethodMatcher {
            /** {@inheritDoc} */
            @Override public abstract boolean matches(Method method, Class cls);

            /** {@inheritDoc} */
            @Override public boolean isRuntime() {
                return false;
            }

            /** {@inheritDoc} */
            // Warning suppression is due to Spring...
            @SuppressWarnings({"RawUseOfParameterizedType"})
            @Override public boolean matches(Method method, Class aClass, Object[] objs) {
                // No-op.
                return false;
            }
        }

}
