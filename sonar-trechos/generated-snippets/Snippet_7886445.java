private static class SetBuckDotFilePathsRequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<SetBuckDotFilePathsRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SetBuckDotFilePathsRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetStampedeId()) {
        optionals.set(0);
      }
      if (struct.isSetDotFiles()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetStampedeId()) {
        struct.stampedeId.write(oprot);
      }
      if (struct.isSetDotFiles()) {
        {
          oprot.writeI32(struct.dotFiles.size());
          for (PathInfo _iter108 : struct.dotFiles)
          {
            _iter108.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SetBuckDotFilePathsRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.stampedeId = new StampedeId();
        struct.stampedeId.read(iprot);
        struct.setStampedeIdIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list109 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.dotFiles = new java.util.ArrayList<PathInfo>(_list109.size);
          PathInfo _elem110;
          for (int _i111 = 0; _i111 < _list109.size; ++_i111)
          {
            _elem110 = new PathInfo();
            _elem110.read(iprot);
            struct.dotFiles.add(_elem110);
          }
        }
        struct.setDotFilesIsSet(true);
      }
    }
  }
