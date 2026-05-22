private static class add_schema_version_resultStandardScheme extends StandardScheme<add_schema_version_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, add_schema_version_result struct) throws org.apache.thrift.TException {
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
                struct.o1 = new AlreadyExistsException();
                struct.o1.read(iprot);
                struct.setO1IsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 2: // O2
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.o2 = new NoSuchObjectException();
                struct.o2.read(iprot);
                struct.setO2IsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 3: // O3
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.o3 = new MetaException();
                struct.o3.read(iprot);
                struct.setO3IsSet(true);
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, add_schema_version_result struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.o1 != null) {
          oprot.writeFieldBegin(O1_FIELD_DESC);
          struct.o1.write(oprot);
          oprot.writeFieldEnd();
        }
        if (struct.o2 != null) {
          oprot.writeFieldBegin(O2_FIELD_DESC);
          struct.o2.write(oprot);
          oprot.writeFieldEnd();
        }
        if (struct.o3 != null) {
          oprot.writeFieldBegin(O3_FIELD_DESC);
          struct.o3.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }
