public abstract class Crc64 extends RExternalBuiltinNode.Arg1 {

    static {
        Casts casts = new Casts(Crc64.class);
        casts.arg(0).mustNotBeNull(RError.Message.INPUT_MUST_BE_STRING).mustBe(stringValue(), RError.Message.INPUT_MUST_BE_STRING);
    }

    @Specialization
    public String crc64(RAbstractStringVector x) {
        return crc(x);
    }

    @TruffleBoundary
    public static String crc(RAbstractStringVector x) {
        final String string = x.getDataAt(0);
        byte[] bytes = string.getBytes();
        bytes = crc64(bytes);
        long l = 0;
        for (int i = 0; i < bytes.length; i++) {
            l += (bytes[i] & 0xffL) << (8 * i);
        }
        return Long.toHexString(l);
    }

    @TruffleBoundary
    private static byte[] crc64(byte[] bytes) {
        org.tukaani.xz.check.CRC64 crc = new org.tukaani.xz.check.CRC64();
        crc.update(bytes);
        return crc.finish();
    }
}
