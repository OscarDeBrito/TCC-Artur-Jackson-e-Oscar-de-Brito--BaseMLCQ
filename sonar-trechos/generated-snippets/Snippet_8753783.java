private static class RewriteConfigsRequestTupleScheme extends TupleScheme<RewriteConfigsRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RewriteConfigsRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetRewriteCommands()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetRewriteCommands()) {
        {
          oprot.writeI32(struct.rewriteCommands.size());
          for (ConfigRewrite _iter404 : struct.rewriteCommands)
          {
            _iter404.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RewriteConfigsRequest struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list405 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.rewriteCommands = new ArrayList<ConfigRewrite>(_list405.size);
          ConfigRewrite _elem406;
          for (int _i407 = 0; _i407 < _list405.size; ++_i407)
          {
            _elem406 = new ConfigRewrite();
            _elem406.read(iprot);
            struct.rewriteCommands.add(_elem406);
          }
        }
        struct.setRewriteCommandsIsSet(true);
      }
    }
  }
