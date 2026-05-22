public class Snippet__9413571 {

    HostAddress[] readAddr() throws IOException, KrbApErrException {
            int numAddrs, addrType, addrLength;
            numAddrs = readLength4();
            if (numAddrs > 0) {
                List<HostAddress> addrs = new ArrayList<>();
                for (int i = 0; i < numAddrs; i++) {
                    addrType = read(2);
                    addrLength = readLength4();
                    if (!(addrLength == 4 || addrLength == 16)) {
                        if (DEBUG) {
                            System.out.println("Incorrect address format.");
                        }
                        return null;
                    }
                    byte[] result = new byte[addrLength];
                    for (int j = 0; j < addrLength; j++)
                        result[j] = (byte)read(1);
                    addrs.add(new HostAddress(addrType, result));
                }
                return addrs.toArray(new HostAddress[addrs.size()]);
            }
            return null;
        }

}
