public class Snippet__8904609 {

    protected void adjustArraySize(int newSize) {
        if (keyStart == null || keyStart.length < newSize) {
          keyStart = new int[newSize];
          keyLength = new int[newSize];
          valueStart = new int[newSize];
          valueLength = new int[newSize];
          keyInited = new boolean[newSize];
          keyIsNull = new boolean[newSize];
          valueInited = new boolean[newSize];
          valueIsNull = new boolean[newSize];
          keyObjects = new LazyBinaryPrimitive<?, ?>[newSize];
          valueObjects = new LazyBinaryObject[newSize];
        }
      }

}
