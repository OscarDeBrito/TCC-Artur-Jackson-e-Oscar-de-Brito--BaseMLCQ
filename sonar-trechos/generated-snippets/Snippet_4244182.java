public class TransparencyFilterTrueColor extends TransparencyFilter {
    private final int transparentColor;

    public TransparencyFilterTrueColor(final byte[] bytes) throws IOException {
        super(bytes);

        final ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        final int transparentRed = read2Bytes("transparentRed", is, "tRNS: Missing transparentColor", getByteOrder());
        final int transparentGreen = read2Bytes("transparentGreen", is, "tRNS: Missing transparentColor", getByteOrder());
        final int transparentBlue = read2Bytes("transparentBlue", is, "tRNS: Missing transparentColor", getByteOrder());

        transparentColor = ((0xff & transparentRed) << 16)
                | ((0xff & transparentGreen) << 8)
                | ((0xff & transparentBlue) << 0);

    }

    @Override
    public int filter(final int rgb, final int sample) throws ImageReadException,
            IOException {
        if ((0x00ffffff & rgb) == transparentColor) {
            return 0x00;
        }

        return rgb;
    }
}
