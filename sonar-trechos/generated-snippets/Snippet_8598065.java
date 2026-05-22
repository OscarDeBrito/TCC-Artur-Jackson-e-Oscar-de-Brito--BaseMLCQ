private static class modifyTable_resultTupleScheme extends org.apache.thrift.scheme.TupleScheme<modifyTable_result> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, modifyTable_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet optionals = new java.util.BitSet();
        if (struct.isSetIo()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetIo()) {
          struct.io.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, modifyTable_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          struct.io = new TIOError();
          struct.io.read(iprot);
          struct.setIoIsSet(true);
        }
      }
    }
