public class Snippet__9484885 {

    protected void createAInfo(ServiceRecord record) throws Exception {
        AContainerRecordDescriptor recordInfo = new AContainerRecordDescriptor(
            getPath(), record);
        registerRecordDescriptor(Type.A, recordInfo);
      }

}
