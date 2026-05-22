public class Snippet__9403827 {

    public static <T, R> R[] mapToArray(Collection<T> inputs, Function<? super T, ? extends R> mapper, IntFunction<R[]> arrayGenerator) {
            R[] result = arrayGenerator.apply(inputs.size());
            int idx = 0;
            for (T t : inputs) {
                result[idx++] = mapper.apply(t);
            }
            return result;
        }

}
