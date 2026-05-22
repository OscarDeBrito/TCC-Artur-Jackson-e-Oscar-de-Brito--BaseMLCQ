public class Snippet__4301165 {

    public byte[] decryptData(byte[] dataEncrypted, int keyUsage) throws GSSException {
            byte[] ret;
            try {
                EncTypeHandler encHandler = EncryptionHandler.getEncHandler(encKey.getKeyType());
                ret = encHandler.decrypt(dataEncrypted, encKey.getKeyData(), keyUsage);
            } catch (KrbException e) {
                throw new GSSException(GSSException.FAILURE, -1, e.getMessage());
            }
            return ret;
        }

}
