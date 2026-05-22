private static class getDataProduct_resultStandardScheme extends StandardScheme<getDataProduct_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, getDataProduct_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 0: // SUCCESS
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.success = new org.apache.airavata.model.data.replica.DataProductModel();
                struct.success.read(iprot);
                struct.setSuccessIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 1: // IRE
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.ire = new org.apache.airavata.model.error.InvalidRequestException();
                struct.ire.read(iprot);
                struct.setIreIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 2: // ACE
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.ace = new org.apache.airavata.model.error.AiravataClientException();
                struct.ace.read(iprot);
                struct.setAceIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 3: // ASE
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.ase = new org.apache.airavata.model.error.AiravataSystemException();
                struct.ase.read(iprot);
                struct.setAseIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 4: // AE
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.ae = new org.apache.airavata.model.error.AuthorizationException();
                struct.ae.read(iprot);
                struct.setAeIsSet(true);
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, getDataProduct_result struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.success != null) {
          oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
          struct.success.write(oprot);
          oprot.writeFieldEnd();
        }
        if (struct.ire != null) {
          oprot.writeFieldBegin(IRE_FIELD_DESC);
          struct.ire.write(oprot);
          oprot.writeFieldEnd();
        }
        if (struct.ace != null) {
          oprot.writeFieldBegin(ACE_FIELD_DESC);
          struct.ace.write(oprot);
          oprot.writeFieldEnd();
        }
        if (struct.ase != null) {
          oprot.writeFieldBegin(ASE_FIELD_DESC);
          struct.ase.write(oprot);
          oprot.writeFieldEnd();
        }
        if (struct.ae != null) {
          oprot.writeFieldBegin(AE_FIELD_DESC);
          struct.ae.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }
