public class Snippet__7880833 {

    @Override
        public void read(org.apache.thrift.protocol.TProtocol prot, Digest struct) throws org.apache.thrift.TException {
          org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
          java.util.BitSet incoming = iprot.readBitSet(2);
          if (incoming.get(0)) {
            struct.hash = iprot.readString();
            struct.setHashIsSet(true);
          }
          if (incoming.get(1)) {
            struct.sizeBytes = iprot.readI64();
            struct.setSizeBytesIsSet(true);
          }
        }

}
