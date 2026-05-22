public class Snippet__4445309 {

    @Override
        public void startField() throws IOException {
          if (!skipNullFields || this.reader.isSet()) {
            if (fieldName.equals(Statistic.ROWCOUNT)
                || fieldName.equals(Statistic.NNROWCOUNT)
                || fieldName.equals(Statistic.NDV)
                || fieldName.equals(Statistic.SUM_DUPS)) {
              nextField = fieldName;
            }
          }
        }

}
