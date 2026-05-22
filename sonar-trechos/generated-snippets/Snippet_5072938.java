public class Snippet__5072938 {

    private abstract static class Formatter {
            long previousTime; // for ThreadLocal caching mode
            int nanos;

            abstract String format(final Instant instant);

            abstract void formatToBuffer(final Instant instant, StringBuilder destination);

            public String toPattern() {
                return null;
            }
        }

}
