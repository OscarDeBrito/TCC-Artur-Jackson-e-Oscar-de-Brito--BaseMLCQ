public class Snippet__5799553 {

    @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
            {
                // args is null for no-arguments methods
                if (args == null)
                {
                    String attributeName = method.getName();

                    if (attributes.containsKey(attributeName)) { return attributes.get(attributeName); }
                }

                // TODO: Handling of equals() and hashCode() and toString(), plus other methods
                // inherited from Object

                throw new RuntimeException(String.format("Annotation proxy for class %s does not handle method %s.",
                        annotationType.getName(), method));
            }

}
