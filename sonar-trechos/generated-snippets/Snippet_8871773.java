private static class list_roles_argsTupleScheme extends TupleScheme<list_roles_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, list_roles_args struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetPrincipal_name()) {
          optionals.set(0);
        }
        if (struct.isSetPrincipal_type()) {
          optionals.set(1);
        }
        oprot.writeBitSet(optionals, 2);
        if (struct.isSetPrincipal_name()) {
          oprot.writeString(struct.principal_name);
        }
        if (struct.isSetPrincipal_type()) {
          oprot.writeI32(struct.principal_type.getValue());
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, list_roles_args struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(2);
        if (incoming.get(0)) {
          struct.principal_name = iprot.readString();
          struct.setPrincipal_nameIsSet(true);
        }
        if (incoming.get(1)) {
          struct.principal_type = org.apache.hadoop.hive.metastore.api.PrincipalType.findByValue(iprot.readI32());
          struct.setPrincipal_typeIsSet(true);
        }
      }
    }
