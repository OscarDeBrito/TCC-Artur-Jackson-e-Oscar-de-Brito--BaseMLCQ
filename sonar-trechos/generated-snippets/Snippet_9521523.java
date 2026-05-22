public class Snippet__9521523 {

    @Override
      public synchronized int read(final byte[] b, final int off, final int len) throws IOException {
        int currentOff = off;
        int currentLen = len;
        int lastReadBytes;
        int totalReadBytes = 0;
        do {
          lastReadBytes = readOneBlock(b, currentOff, currentLen);
          if (lastReadBytes > 0) {
            currentOff += lastReadBytes;
            currentLen -= lastReadBytes;
            totalReadBytes += lastReadBytes;
          }
          if (currentLen <= 0 || currentLen > b.length - currentOff) {
            break;
          }
        } while (lastReadBytes > 0);
        return totalReadBytes > 0 ? totalReadBytes : lastReadBytes;
      }

}
