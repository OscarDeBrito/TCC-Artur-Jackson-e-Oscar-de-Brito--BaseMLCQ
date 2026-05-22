public class Snippet__8650167 {

    public static boolean isSignedFloat(String s) {
            if (isEmpty(s)) {
                return defaultEmptyOK;
            }
            try {
                Float.parseFloat(s);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

}
