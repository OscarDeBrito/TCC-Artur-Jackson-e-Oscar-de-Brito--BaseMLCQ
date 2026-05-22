public class Snippet__9293709 {

    public static String test(int arg) throws NoSuchMethodException {
            if (arg == 0) {
                return Class_getMethod02.class.getMethod("test").getName();
            } else if (arg == 1) {
                return Class_getMethod02.class.getMethod("test", int.class).getName();
            } else if (arg == 2) {
                return Class_getMethod02.class.getMethod("main").getName();
            } else if (arg == 3) {
                return Class_getMethod02.class.getMethod("main", String[].class).getName();
            } else if (arg == 4) {
                return Class_getMethod02.class.getMethod("<init>").getName();
            } else if (arg == 5) {
                return Class_getMethod02.class.getMethod("<clinit>").getName();
            }
            return null;
        }

}
