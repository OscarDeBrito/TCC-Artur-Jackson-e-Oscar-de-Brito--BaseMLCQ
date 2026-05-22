public class Snippet__4113404 {

    @SuppressWarnings("deprecation")
        private static Maybe<Calendar> parseCalendarDefaultParse(String input) {
            try {
                long ms = Date.parse(input);
                if (ms>=new Date(1999, 12, 25).getTime() && ms <= new Date(2200, 1, 2).getTime()) {
                    // accept default date parse for this century and next
                    GregorianCalendar c = new GregorianCalendar();
                    c.setTimeInMillis(ms);
                    return Maybe.of((Calendar)c);
                }
            } catch (Exception e) {
                Exceptions.propagateIfFatal(e);
            }
            return Maybe.absent();
        }

}
