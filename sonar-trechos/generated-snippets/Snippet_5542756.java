public class Snippet__5542756 {

    @Override
          public void write(org.apache.thrift.protocol.TProtocol prot, list_roles_privileges_result struct) throws org.apache.thrift.TException {
            TTupleProtocol oprot = (TTupleProtocol) prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
              optionals.set(0);
            }
            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
              struct.success.write(oprot);
            }
          }

}
