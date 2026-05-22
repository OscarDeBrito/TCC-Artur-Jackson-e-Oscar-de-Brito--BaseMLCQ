private static class put_argsStandardScheme extends org.apache.thrift.scheme.StandardScheme<put_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, put_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // TABLE
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.table = iprot.readBinary();
                struct.setTableIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 2: // TPUT
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.tput = new TPut();
                struct.tput.read(iprot);
                struct.setTputIsSet(true);
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

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, put_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.table != null) {
          oprot.writeFieldBegin(TABLE_FIELD_DESC);
          oprot.writeBinary(struct.table);
          oprot.writeFieldEnd();
        }
        if (struct.tput != null) {
          oprot.writeFieldBegin(TPUT_FIELD_DESC);
          struct.tput.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }
