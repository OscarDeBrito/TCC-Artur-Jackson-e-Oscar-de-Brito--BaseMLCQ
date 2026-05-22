private static class getUserComputeResourcePreference_argsStandardScheme extends StandardScheme<getUserComputeResourcePreference_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, getUserComputeResourcePreference_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // USER_ID
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.userId = iprot.readString();
                struct.setUserIdIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 2: // GATEWAY_ID
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.gatewayID = iprot.readString();
                struct.setGatewayIDIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 3: // USER_COMPUTE_RESOURCE_ID
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.userComputeResourceId = iprot.readString();
                struct.setUserComputeResourceIdIsSet(true);
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, getUserComputeResourcePreference_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.userId != null) {
          oprot.writeFieldBegin(USER_ID_FIELD_DESC);
          oprot.writeString(struct.userId);
          oprot.writeFieldEnd();
        }
        if (struct.gatewayID != null) {
          oprot.writeFieldBegin(GATEWAY_ID_FIELD_DESC);
          oprot.writeString(struct.gatewayID);
          oprot.writeFieldEnd();
        }
        if (struct.userComputeResourceId != null) {
          oprot.writeFieldBegin(USER_COMPUTE_RESOURCE_ID_FIELD_DESC);
          oprot.writeString(struct.userComputeResourceId);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }
