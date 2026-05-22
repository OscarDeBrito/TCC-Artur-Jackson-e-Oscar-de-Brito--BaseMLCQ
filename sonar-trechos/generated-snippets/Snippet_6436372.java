public class Snippet__6436372 {

    @Nullable
        static Integer doubleToInt32(@Nonnull Double d) {
            long rounded = Math.round(d);

            if (rounded >= Integer.MIN_VALUE && rounded <= Integer.MAX_VALUE) {
                return (int) rounded;
            } else {
                return null;
            }
        }

}
