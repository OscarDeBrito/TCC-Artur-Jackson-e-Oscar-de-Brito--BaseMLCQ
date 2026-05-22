private static class check_hms_seq_num_argsStandardScheme extends StandardScheme<check_hms_seq_num_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, check_hms_seq_num_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // PATH_SEQ_NUM
              if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
                struct.pathSeqNum = iprot.readI64();
                struct.setPathSeqNumIsSet(true);
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, check_hms_seq_num_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        oprot.writeFieldBegin(PATH_SEQ_NUM_FIELD_DESC);
        oprot.writeI64(struct.pathSeqNum);
        oprot.writeFieldEnd();
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }
