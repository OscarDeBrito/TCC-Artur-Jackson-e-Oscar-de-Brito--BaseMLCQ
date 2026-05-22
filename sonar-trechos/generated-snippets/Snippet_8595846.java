public class Snippet__8595846 {

    public static void encrypt(OutputStream out, byte[] src, int offset,
          int length, Encryptor e) throws IOException {
        OutputStream cout = e.createEncryptionStream(out);
        try {
          cout.write(src, offset, length);
        } finally {
          cout.close();
        }
      }

}
