public class Snippet__4242573 {

    public static void throwCauseIfTaggedWith(final Throwable throwable, final Object tag)
                throws IOException {
            if (isTaggedWith(throwable, tag)) {
                throw ((TaggedIOException) throwable).getCause();
            }
        }

}
