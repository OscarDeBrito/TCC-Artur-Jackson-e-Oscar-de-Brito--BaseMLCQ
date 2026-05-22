public class Snippet__8602498 {

    public DataBlockEncoder getEncoder() {
        if (encoder == null && id != 0) {
          // lazily create the encoder
          encoder = createEncoder(encoderCls);
        }
        return encoder;
      }

}
