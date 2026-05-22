private static class get_schema_with_environment_context_argsStandardScheme extends StandardScheme<get_schema_with_environment_context_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, get_schema_with_environment_context_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // DB_NAME
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.db_name = iprot.readString();
                struct.setDb_nameIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 2: // TABLE_NAME
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.table_name = iprot.readString();
                struct.setTable_nameIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 3: // ENVIRONMENT_CONTEXT
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.environment_context = new EnvironmentContext();
                struct.environment_context.read(iprot);
                struct.setEnvironment_contextIsSet(true);
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, get_schema_with_environment_context_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.db_name != null) {
          oprot.writeFieldBegin(DB_NAME_FIELD_DESC);
          oprot.writeString(struct.db_name);
          oprot.writeFieldEnd();
        }
        if (struct.table_name != null) {
          oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
          oprot.writeString(struct.table_name);
          oprot.writeFieldEnd();
        }
        if (struct.environment_context != null) {
          oprot.writeFieldBegin(ENVIRONMENT_CONTEXT_FIELD_DESC);
          struct.environment_context.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }
