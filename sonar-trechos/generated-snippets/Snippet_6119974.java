public class Snippet__6119974 {

    private static final class NullSafeFunction<T, V> implements Function<T, V>
        {
            private static final long serialVersionUID = 1L;
            private final Function<? super T, ? extends V> function;
            private final V nullValue;

            private NullSafeFunction(Function<? super T, ? extends V> function, V nullValue)
            {
                this.function = function;
                this.nullValue = nullValue;
            }

            @Override
            public V valueOf(T object)
            {
                return object == null ? this.nullValue : this.function.valueOf(object);
            }
        }

}
