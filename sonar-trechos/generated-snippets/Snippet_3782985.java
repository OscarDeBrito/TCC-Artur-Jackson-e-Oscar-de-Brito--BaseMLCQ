public class Snippet__3782985 {

    private static boolean parseMsAcmCodecPrivate(ParsableByteArray buffer) throws ParserException {
          try {
            int formatTag = buffer.readLittleEndianUnsignedShort();
            if (formatTag == WAVE_FORMAT_PCM) {
              return true;
            } else if (formatTag == WAVE_FORMAT_EXTENSIBLE) {
              buffer.setPosition(WAVE_FORMAT_SIZE + 6); // unionSamples(2), channelMask(4)
              return buffer.readLong() == WAVE_SUBFORMAT_PCM.getMostSignificantBits()
                  && buffer.readLong() == WAVE_SUBFORMAT_PCM.getLeastSignificantBits();
            } else {
              return false;
            }
          } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParserException("Error parsing MS/ACM codec private");
          }
        }

}
