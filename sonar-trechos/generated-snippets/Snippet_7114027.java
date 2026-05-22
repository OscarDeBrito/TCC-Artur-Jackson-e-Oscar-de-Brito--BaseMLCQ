public class Snippet__7114027 {

    public static String join(CharSequence delimiter, Iterable tokens) {
            StringBuilder sb = new StringBuilder();
            boolean firstTime = true;
            for (Object token: tokens) {
                if (firstTime) {
                    firstTime = false;
                } else {
                    sb.append(delimiter);
                }
                sb.append(token);
            }
            return sb.toString();
        }

}
