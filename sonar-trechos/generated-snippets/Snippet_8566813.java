public class Snippet__8566813 {

    protected WALHdrContext readHeader(Builder builder, FSDataInputStream stream)
          throws IOException {
         boolean res = builder.mergeDelimitedFrom(stream);
         if (!res) return new WALHdrContext(WALHdrResult.EOF, null);
         if (builder.hasWriterClsName() &&
             !getWriterClsNames().contains(builder.getWriterClsName())) {
           return new WALHdrContext(WALHdrResult.UNKNOWN_WRITER_CLS, null);
         }
         String clsName = null;
         if (builder.hasCellCodecClsName()) {
           clsName = builder.getCellCodecClsName();
         }
         return new WALHdrContext(WALHdrResult.SUCCESS, clsName);
      }

}
