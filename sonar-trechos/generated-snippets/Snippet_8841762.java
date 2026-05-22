public class Snippet__8841762 {

    @Override
          public void write(org.apache.thrift.protocol.TProtocol prot, getEntity_result struct) throws org.apache.thrift.TException {
            TTupleProtocol oprot = (TTupleProtocol) prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
              optionals.set(0);
            }
            if (struct.isSetSre()) {
              optionals.set(1);
            }
            oprot.writeBitSet(optionals, 2);
            if (struct.isSetSuccess()) {
              struct.success.write(oprot);
            }
            if (struct.isSetSre()) {
              struct.sre.write(oprot);
            }
          }

}
