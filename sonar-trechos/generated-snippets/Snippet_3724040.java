public class Snippet__3724040 {

    public static String getCauseMessage(Throwable t) {
            if (null != t.getCause()) {
                return getCauseMessage(t.getCause());
            }
            return t.getMessage();
        }

}
