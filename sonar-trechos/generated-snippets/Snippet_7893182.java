public class Snippet__7893182 {

    private Snippet__7893182(Bucket[] buckets, byte[] stringTable, int numEntries, int maxValueLength) {
        Preconditions.checkArgument(buckets.length > 0, "The number of buckets must be greater than 0");
        Preconditions.checkArgument(
            (buckets.length & (buckets.length - 1)) == 0, "The number of buckets must be a power of 2");

        this.buckets = buckets;
        this.stringBytes = stringTable;
        this.numEntries = numEntries;
        this.maxValueLength = maxValueLength;
      }

}
