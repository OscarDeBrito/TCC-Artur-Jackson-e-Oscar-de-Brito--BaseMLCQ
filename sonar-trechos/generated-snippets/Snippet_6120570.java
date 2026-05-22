public class Snippet__6120570 {

    public static final class FloatFunctionChain<T1, T2> implements FloatFunction<T1>
        {
            private static final long serialVersionUID = 1L;
            private final Function<T1, T2> function1;
            private final FloatFunction<? super T2> function2;

            private FloatFunctionChain(Function<T1, T2> function1, FloatFunction<? super T2> function2)
            {
                this.function1 = function1;
                this.function2 = function2;
            }

            @Override
            public float floatValueOf(T1 object)
            {
                return this.function2.floatValueOf(this.function1.valueOf(object));
            }
        }

}
