public class Snippet__7757865 {

    private boolean isMoreToReadBeforeReadingRecord(
          final SequenceOffsetType recordSequenceNumber,
          final SequenceOffsetType endSequenceNumber
      )
      {
        final int compareToEnd = createSequenceNumber(recordSequenceNumber)
            .compareTo(createSequenceNumber(endSequenceNumber));

        return isEndOffsetExclusive() ? compareToEnd < 0 : compareToEnd <= 0;
      }

}
