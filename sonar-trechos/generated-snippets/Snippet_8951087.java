public class Snippet__8951087 {

    @Override
      public boolean isNull(int keyIndex) {
        if (keyIndex == 0) {
          return isNull0;
        } else if (keyIndex == 1) {
          return isNull1;
        } else {
          throw new ArrayIndexOutOfBoundsException();
        }
      }

}
