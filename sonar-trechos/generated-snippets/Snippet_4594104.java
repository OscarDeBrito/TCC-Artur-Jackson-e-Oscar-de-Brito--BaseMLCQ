public class Snippet__4594104 {

    public void forEachIntByte(IntByteConsumer f) {
        for (int i = 0; i < size(); ++i) {
          f.apply(keys[i], values[i]);
        }
      }

}
