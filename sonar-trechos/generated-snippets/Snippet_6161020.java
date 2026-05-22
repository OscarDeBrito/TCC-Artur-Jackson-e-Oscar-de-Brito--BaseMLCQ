public class Snippet__6161020 {

    public Path removeLastSegments(int count) {
        if (count == 0) return this;
        if (count >= segments.length) {
          // result will have no trailing separator
          return new Path(device, NO_SEGMENTS, separators & (HAS_LEADING | IS_UNC));
        }
        checkArgument(count > 0);
        int newSize = segments.length - count;
        String[] newSegments = new String[newSize];
        System.arraycopy(this.segments, 0, newSegments, 0, newSize);
        return new Path(device, newSegments, separators & (HAS_LEADING | IS_UNC));
      }

}
