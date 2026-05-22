private static class registerStorageResource_argsStandardScheme extends StandardScheme<registerStorageResource_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, registerStorageResource_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // AUTHZ_TOKEN
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.authzToken = new org.apache.airavata.model.security.AuthzToken();
                struct.authzToken.read(iprot);
                struct.setAuthzTokenIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 2: // STORAGE_RESOURCE_DESCRIPTION
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.storageResourceDescription = new org.apache.airavata.model.appcatalog.storageresource.StorageResourceDescription();
                struct.storageResourceDescription.read(iprot);
                struct.setStorageResourceDescriptionIsSet(true);
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, registerStorageResource_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.authzToken != null) {
          oprot.writeFieldBegin(AUTHZ_TOKEN_FIELD_DESC);
          struct.authzToken.write(oprot);
          oprot.writeFieldEnd();
        }
        if (struct.storageResourceDescription != null) {
          oprot.writeFieldBegin(STORAGE_RESOURCE_DESCRIPTION_FIELD_DESC);
          struct.storageResourceDescription.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }
