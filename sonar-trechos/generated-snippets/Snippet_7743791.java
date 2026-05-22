public class Snippet__7743791 {

    private static boolean isMulticastAddress(String ip) {
            int i = ip.indexOf('.');
            if (i > 0) {
                String prefix = ip.substring(0, i);
                if (StringUtils.isInteger(prefix)) {
                    int p = Integer.parseInt(prefix);
                    return p >= 224 && p <= 239;
                }
            }
            return false;
        }

}
