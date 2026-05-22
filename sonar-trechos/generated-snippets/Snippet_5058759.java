public final class NameUtil {

    private static final int MASK = 0xff;

    private NameUtil() {
    }

    public static String getSubName(final String name) {
        if (Strings.isEmpty(name)) {
            return null;
        }
        final int i = name.lastIndexOf('.');
        return i > 0 ? name.substring(0, i) : Strings.EMPTY;
    }

    public static String md5(final String string) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(string.getBytes());
            final byte[] bytes = digest.digest();
            final StringBuilder md5 = new StringBuilder();
            for (final byte b : bytes) {
                final String hex = Integer.toHexString(MASK & b);
                if (hex.length() == 1) {
                    md5.append('0');
                }
                md5.append(hex);
            }
            return md5.toString();
        } catch (final Exception ex) {
            return string;
        }
    }
}
