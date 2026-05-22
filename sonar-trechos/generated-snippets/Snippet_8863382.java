public class Snippet__8863382 {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TCancelOperationResp struct) throws org.apache.thrift.TException {
          org.apache.thrift.protocol.TField schemeField;
          iprot.readStructBegin();
          while (true)
          {
            schemeField = iprot.readFieldBegin();
            if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
              break;
            }
            switch (schemeField.id) {
              case 1: // STATUS
                if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                  struct.status = new TStatus();
                  struct.status.read(iprot);
                  struct.setStatusIsSet(true);
                } else { 
                  org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                }
                break;
              default:
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            iprot.readFieldEnd();
          }
          iprot.readStructEnd();
          struct.validate();
        }

}
