private static class getRowTs_argsStandardScheme extends org.apache.thrift.scheme.StandardScheme<getRowTs_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, getRowTs_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // TABLE_NAME
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.tableName = iprot.readBinary();
                struct.setTableNameIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 2: // ROW
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.row = iprot.readBinary();
                struct.setRowIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 3: // TIMESTAMP
              if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
                struct.timestamp = iprot.readI64();
                struct.setTimestampIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 4: // ATTRIBUTES
              if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
                {
                  org.apache.thrift.protocol.TMap _map182 = iprot.readMapBegin();
                  struct.attributes = new java.util.HashMap<java.nio.ByteBuffer,java.nio.ByteBuffer>(2*_map182.size);
                  @org.apache.thrift.annotation.Nullable java.nio.ByteBuffer _key183;
                  @org.apache.thrift.annotation.Nullable java.nio.ByteBuffer _val184;
                  for (int _i185 = 0; _i185 < _map182.size; ++_i185)
                  {
                    _key183 = iprot.readBinary();
                    _val184 = iprot.readBinary();
                    struct.attributes.put(_key183, _val184);
                  }
                  iprot.readMapEnd();
                }
                struct.setAttributesIsSet(true);
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, getRowTs_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.tableName != null) {
          oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
          oprot.writeBinary(struct.tableName);
          oprot.writeFieldEnd();
        }
        if (struct.row != null) {
          oprot.writeFieldBegin(ROW_FIELD_DESC);
          oprot.writeBinary(struct.row);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
        oprot.writeI64(struct.timestamp);
        oprot.writeFieldEnd();
        if (struct.attributes != null) {
          oprot.writeFieldBegin(ATTRIBUTES_FIELD_DESC);
          {
            oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, struct.attributes.size()));
            for (java.util.Map.Entry<java.nio.ByteBuffer, java.nio.ByteBuffer> _iter186 : struct.attributes.entrySet())
            {
              oprot.writeBinary(_iter186.getKey());
              oprot.writeBinary(_iter186.getValue());
            }
            oprot.writeMapEnd();
          }
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }
