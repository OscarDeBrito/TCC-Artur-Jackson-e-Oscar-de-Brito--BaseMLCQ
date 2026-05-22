public class Snippet__3796407 {

    public void read(org.apache.thrift.protocol.TProtocol iprot, getNettyMetrics_result struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TField schemeField;
            iprot.readStructBegin();
            while (true)
            {
              schemeField = iprot.readFieldBegin();
              if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
                break;
              }
              switch (schemeField.id) {
                case 0: // SUCCESS
                  if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                    struct.success = new MetricInfo();
                    struct.success.read(iprot);
                    struct.set_success_isSet(true);
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
