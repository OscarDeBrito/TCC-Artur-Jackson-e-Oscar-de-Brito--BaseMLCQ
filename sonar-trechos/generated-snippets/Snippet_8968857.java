private static class GetValidWriteIdsResponseStandardScheme extends StandardScheme<GetValidWriteIdsResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, GetValidWriteIdsResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TBL_VALID_WRITE_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list658 = iprot.readListBegin();
                struct.tblValidWriteIds = new ArrayList<TableValidWriteIds>(_list658.size);
                TableValidWriteIds _elem659;
                for (int _i660 = 0; _i660 < _list658.size; ++_i660)
                {
                  _elem659 = new TableValidWriteIds();
                  _elem659.read(iprot);
                  struct.tblValidWriteIds.add(_elem659);
                }
                iprot.readListEnd();
              }
              struct.setTblValidWriteIdsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, GetValidWriteIdsResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.tblValidWriteIds != null) {
        oprot.writeFieldBegin(TBL_VALID_WRITE_IDS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.tblValidWriteIds.size()));
          for (TableValidWriteIds _iter661 : struct.tblValidWriteIds)
          {
            _iter661.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }
