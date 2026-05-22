public class SnapshotListResponseHandler extends BaseSnapshotResponseHandler<List<Snapshot>> {

   private final List<Snapshot> snapshots;

   @Inject
   SnapshotListResponseHandler(DateService dateService) {
      super(dateService);
      this.snapshots = Lists.newArrayList();
   }

   @Override
   public void endElement(String uri, String localName, String qName) throws SAXException {
      setPropertyOnEndTag(qName);
      if ("return".equals(qName)) {
         snapshots.add(builder.build());
         builder = Snapshot.builder();
      }
      clearTextBuffer();
   }

   @Override
   public List<Snapshot> getResult() {
      return snapshots;
   }

}
