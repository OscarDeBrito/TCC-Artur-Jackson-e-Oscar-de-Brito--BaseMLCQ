public class Snippet__8903115 {

    @Override
      public int compareTo(RecordIdentifier other) {
        if (other.getClass() != RecordIdentifier.class) {
          //WTF?  assumes that other instanceof OrcRawRecordMerger.ReaderKey???
          return -other.compareTo(this);
        }
        return compareToInternal(other);
      }

}
