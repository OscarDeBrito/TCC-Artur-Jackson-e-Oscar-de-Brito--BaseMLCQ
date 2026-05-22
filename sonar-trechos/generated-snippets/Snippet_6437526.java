public class StreamUtil {

    /**
     * Turns an Optional into a Stream of length zero or one depending upon whether a value is present.
     */
    public static <T> Stream<T> opt2stream(Optional<T> opt) {
        return opt.map(Stream::of).orElseGet(Stream::empty);
    }

}
