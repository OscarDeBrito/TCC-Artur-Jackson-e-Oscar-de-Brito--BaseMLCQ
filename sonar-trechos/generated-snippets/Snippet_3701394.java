public class Snippet__3701394 {

    public final int getBeUint24(final int pos) {
            final int position = origin + pos;

            if (pos + 2 >= limit || pos < 0) throw new IllegalArgumentException("limit excceed: "
                                                                                + (pos < 0 ? pos : (pos + 2)));

            byte[] buf = buffer;
            return (0xff & buf[position + 2]) | ((0xff & buf[position + 1]) << 8) | ((0xff & buf[position]) << 16);
        }

}
