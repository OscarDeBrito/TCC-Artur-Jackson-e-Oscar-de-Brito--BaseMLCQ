public class Snippet__8873169 {

    public void read(org.apache.thrift.protocol.TProtocol iprot, add_check_constraint_result struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TField schemeField;
            iprot.readStructBegin();
            while (true)
            {
              schemeField = iprot.readFieldBegin();
              if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
                break;
              }
              switch (schemeField.id) {
                case 1: // O1
                  if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                    struct.o1 = new NoSuchObjectException();
                    struct.o1.read(iprot);
                    struct.setO1IsSet(true);
                  } else { 
                    org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
                case 2: // O2
                  if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                    struct.o2 = new MetaException();
                    struct.o2.read(iprot);
                    struct.setO2IsSet(true);
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
