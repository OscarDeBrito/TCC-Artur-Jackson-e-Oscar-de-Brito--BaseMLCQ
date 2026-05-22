public class Snippet__4739090 {

    @Nonnull
        public static double[] set(@Nonnull double[] src, final int index, final double value) {
            if (index >= src.length) {
                src = Arrays.copyOf(src, src.length * 2);
            }
            src[index] = value;
            return src;
        }

}
