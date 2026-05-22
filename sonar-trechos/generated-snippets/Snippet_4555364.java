public class Snippet__4555364 {

    public static boolean isInfinite(Number num) {
            if (num instanceof Double) {
                return ((Double) num).isInfinite();
            } else if (num instanceof Float) {
                return ((Float) num).isInfinite();
            } else if (hasTypeThatIsKnownToNotSupportInfiniteAndNaN(num)) {
                return false;
            } else {
                throw new UnsupportedNumberClassException(num.getClass());
            }           
        }

}
