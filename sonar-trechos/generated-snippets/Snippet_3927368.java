public class Snippet__3927368 {

    @Override
          public void read(org.apache.thrift.protocol.TProtocol prot, cancelCompaction_args struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
            java.util.BitSet incoming = iprot.readBitSet(2);
            if (incoming.get(0)) {
              struct.login = iprot.readBinary();
              struct.setLoginIsSet(true);
            }
            if (incoming.get(1)) {
              struct.tableName = iprot.readString();
              struct.setTableNameIsSet(true);
            }
          }

}
