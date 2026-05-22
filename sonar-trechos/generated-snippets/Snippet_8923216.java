public class NotTezEventHelper {

  public static Signable createSignableNotTezEvent(
      InputDataInformationEvent event, String vertexName, String destInputName) {
    final NotTezEvent.Builder builder = NotTezEvent.newBuilder().setInputEventProtoBytes(
        ProtoConverters.convertRootInputDataInformationEventToProto(event).toByteString())
        .setVertexName(vertexName).setDestInputName(destInputName);
    return new Signable() {
      @Override
      public void setSignInfo(int masterKeyId) {
        builder.setKeyId(masterKeyId);
      }

      @Override
      public byte[] serialize() throws IOException {
        NotTezEvent nte = builder.build();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(nte.getSerializedSize());
        nte.writeTo(baos);
        return baos.toByteArray();
      }
    };
  }

  public static TezEvent toTezEvent(NotTezEvent nte) throws InvalidProtocolBufferException {
    EventMetaData sourceMetaData = new EventMetaData(EventMetaData.EventProducerConsumerType.INPUT,
        nte.getVertexName(), "NULL_VERTEX", null);
    EventMetaData destMetaData = new EventMetaData(EventMetaData.EventProducerConsumerType.INPUT,
        nte.getVertexName(), nte.getDestInputName(), null);
    InputDataInformationEvent event = ProtoConverters.convertRootInputDataInformationEventFromProto(
        RootInputDataInformationEventProto.parseFrom(nte.getInputEventProtoBytes()));
    TezEvent tezEvent = new TezEvent(event, sourceMetaData, System.currentTimeMillis());
    tezEvent.setDestinationInfo(destMetaData);
    return tezEvent;
  }
}
