public class Snippet__4849963 {

    private static final class CipherStorageOutputStream extends
                StorageOutputStream {
            private final StorageOutputStream storageOut;
            private final String algorithm;
            private final SecretKeySpec skeySpec;
            private final CipherOutputStream cipherOut;

            public CipherStorageOutputStream(StorageOutputStream out,
                    String algorithm, SecretKeySpec skeySpec) throws IOException {
                try {
                    this.storageOut = out;
                    this.algorithm = algorithm;
                    this.skeySpec = skeySpec;

                    Cipher cipher = Cipher.getInstance(algorithm);
                    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

                    this.cipherOut = new CipherOutputStream(out, cipher);
                } catch (GeneralSecurityException e) {
                    throw (IOException) new IOException().initCause(e);
                }
            }

            @Override
            public void close() throws IOException {
                super.close();
                cipherOut.close();
            }

            @Override
            protected void write0(byte[] buffer, int offset, int length)
                    throws IOException {
                cipherOut.write(buffer, offset, length);
            }

            @Override
            protected Storage toStorage0() throws IOException {
                // cipherOut has already been closed because toStorage calls close
                Storage encrypted = storageOut.toStorage();
                return new CipherStorage(encrypted, algorithm, skeySpec);
            }
        }

}
