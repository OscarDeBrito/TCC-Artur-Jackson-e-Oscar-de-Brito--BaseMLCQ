public class Snippet__5785684 {

    @Override
      public void toSummaryProtoStream(OutputStream outputStream) throws IOException {
        ProtoUtils.toSummaryEventProto(dagID, commitStartTime,
            getEventType(), null).writeDelimitedTo(outputStream);
      }

}
