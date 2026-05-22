private static class GetPartitionsRequestStandardScheme extends StandardScheme<GetPartitionsRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, GetPartitionsRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CAT_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.catName = iprot.readString();
              struct.setCatNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DB_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.dbName = iprot.readString();
              struct.setDbNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TBL_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.tblName = iprot.readString();
              struct.setTblNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // WITH_AUTH
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.withAuth = iprot.readBool();
              struct.setWithAuthIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // USER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.user = iprot.readString();
              struct.setUserIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // GROUP_NAMES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list1024 = iprot.readListBegin();
                struct.groupNames = new ArrayList<String>(_list1024.size);
                String _elem1025;
                for (int _i1026 = 0; _i1026 < _list1024.size; ++_i1026)
                {
                  _elem1025 = iprot.readString();
                  struct.groupNames.add(_elem1025);
                }
                iprot.readListEnd();
              }
              struct.setGroupNamesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // PROJECTION_SPEC
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.projectionSpec = new GetPartitionsProjectionSpec();
              struct.projectionSpec.read(iprot);
              struct.setProjectionSpecIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // FILTER_SPEC
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.filterSpec = new GetPartitionsFilterSpec();
              struct.filterSpec.read(iprot);
              struct.setFilterSpecIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, GetPartitionsRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.catName != null) {
        if (struct.isSetCatName()) {
          oprot.writeFieldBegin(CAT_NAME_FIELD_DESC);
          oprot.writeString(struct.catName);
          oprot.writeFieldEnd();
        }
      }
      if (struct.dbName != null) {
        oprot.writeFieldBegin(DB_NAME_FIELD_DESC);
        oprot.writeString(struct.dbName);
        oprot.writeFieldEnd();
      }
      if (struct.tblName != null) {
        oprot.writeFieldBegin(TBL_NAME_FIELD_DESC);
        oprot.writeString(struct.tblName);
        oprot.writeFieldEnd();
      }
      if (struct.isSetWithAuth()) {
        oprot.writeFieldBegin(WITH_AUTH_FIELD_DESC);
        oprot.writeBool(struct.withAuth);
        oprot.writeFieldEnd();
      }
      if (struct.user != null) {
        if (struct.isSetUser()) {
          oprot.writeFieldBegin(USER_FIELD_DESC);
          oprot.writeString(struct.user);
          oprot.writeFieldEnd();
        }
      }
      if (struct.groupNames != null) {
        if (struct.isSetGroupNames()) {
          oprot.writeFieldBegin(GROUP_NAMES_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.groupNames.size()));
            for (String _iter1027 : struct.groupNames)
            {
              oprot.writeString(_iter1027);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.projectionSpec != null) {
        oprot.writeFieldBegin(PROJECTION_SPEC_FIELD_DESC);
        struct.projectionSpec.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.filterSpec != null) {
        oprot.writeFieldBegin(FILTER_SPEC_FIELD_DESC);
        struct.filterSpec.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }
