public class Snippet__3701417 {

    public final long getUlong56() {
            if (position + 6 >= origin + limit) throw new IllegalArgumentException("limit excceed: "
                                                                                   + (position - origin + 6));

            byte[] buf = buffer;
            return ((long) (0xff & buf[position++])) | ((long) (0xff & buf[position++]) << 8)
                   | ((long) (0xff & buf[position++]) << 16) | ((long) (0xff & buf[position++]) << 24)
                   | ((long) (0xff & buf[position++]) << 32) | ((long) (0xff & buf[position++]) << 40)
                   | ((long) (0xff & buf[position++]) << 48);
        }

}
