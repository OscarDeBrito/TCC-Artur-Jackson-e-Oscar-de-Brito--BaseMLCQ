public class Snippet__8963199 {

    private byte[] byteVars() {
        if (byteVars.length == size) {
          byte[] newVars = new byte[size << 1];
          System.arraycopy(byteVars, 0, newVars, 0, size);
          return byteVars = newVars;
        }
        return byteVars;
      }

}
