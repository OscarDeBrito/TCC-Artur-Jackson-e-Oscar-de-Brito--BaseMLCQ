public class Snippet__3817294 {

    private byte getByte(final long index) {
          checkForPacket(index);

          if (fileCache != null && index < packetPosition) {
             return fileCache.getByteFromCache(index);
          } else {
             return currentPacket.getChunk()[(int) (index - packetPosition)];
          }
       }

}
