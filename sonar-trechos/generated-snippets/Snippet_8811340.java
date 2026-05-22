public static class isSSHSetupCompleteForUserComputeResourcePreference_args implements org.apache.thrift.TBase<isSSHSetupCompleteForUserComputeResourcePreference_args, isSSHSetupCompleteForUserComputeResourcePreference_args._Fields>, java.io.Serializable, Cloneable, Comparable<isSSHSetupCompleteForUserComputeResourcePreference_args>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("isSSHSetupCompleteForUserComputeResourcePreference_args");

    private static final org.apache.thrift.protocol.TField AUTHZ_TOKEN_FIELD_DESC = new org.apache.thrift.protocol.TField("authzToken", org.apache.thrift.protocol.TType.STRUCT, (short)1);
    private static final org.apache.thrift.protocol.TField COMPUTE_RESOURCE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("computeResourceId", org.apache.thrift.protocol.TType.STRING, (short)2);
    private static final org.apache.thrift.protocol.TField AIRAVATA_CRED_STORE_TOKEN_FIELD_DESC = new org.apache.thrift.protocol.TField("airavataCredStoreToken", org.apache.thrift.protocol.TType.STRING, (short)3);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new isSSHSetupCompleteForUserComputeResourcePreference_argsStandardSchemeFactory());
      schemes.put(TupleScheme.class, new isSSHSetupCompleteForUserComputeResourcePreference_argsTupleSchemeFactory());
    }

    public org.apache.airavata.model.security.AuthzToken authzToken; // required
    public String computeResourceId; // required
    public String airavataCredStoreToken; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      AUTHZ_TOKEN((short)1, "authzToken"),
      COMPUTE_RESOURCE_ID((short)2, "computeResourceId"),
      AIRAVATA_CRED_STORE_TOKEN((short)3, "airavataCredStoreToken");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 1: // AUTHZ_TOKEN
            return AUTHZ_TOKEN;
          case 2: // COMPUTE_RESOURCE_ID
            return COMPUTE_RESOURCE_ID;
          case 3: // AIRAVATA_CRED_STORE_TOKEN
            return AIRAVATA_CRED_STORE_TOKEN;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.AUTHZ_TOKEN, new org.apache.thrift.meta_data.FieldMetaData("authzToken", org.apache.thrift.TFieldRequirementType.REQUIRED, 
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, org.apache.airavata.model.security.AuthzToken.class)));
      tmpMap.put(_Fields.COMPUTE_RESOURCE_ID, new org.apache.thrift.meta_data.FieldMetaData("computeResourceId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
      tmpMap.put(_Fields.AIRAVATA_CRED_STORE_TOKEN, new org.apache.thrift.meta_data.FieldMetaData("airavataCredStoreToken", org.apache.thrift.TFieldRequirementType.REQUIRED, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(isSSHSetupCompleteForUserComputeResourcePreference_args.class, metaDataMap);
    }

    public isSSHSetupCompleteForUserComputeResourcePreference_args() {
    }

    public isSSHSetupCompleteForUserComputeResourcePreference_args(
      org.apache.airavata.model.security.AuthzToken authzToken,
      String computeResourceId,
      String airavataCredStoreToken)
    {
      this();
      this.authzToken = authzToken;
      this.computeResourceId = computeResourceId;
      this.airavataCredStoreToken = airavataCredStoreToken;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public isSSHSetupCompleteForUserComputeResourcePreference_args(isSSHSetupCompleteForUserComputeResourcePreference_args other) {
      if (other.isSetAuthzToken()) {
        this.authzToken = new org.apache.airavata.model.security.AuthzToken(other.authzToken);
      }
      if (other.isSetComputeResourceId()) {
        this.computeResourceId = other.computeResourceId;
      }
      if (other.isSetAiravataCredStoreToken()) {
        this.airavataCredStoreToken = other.airavataCredStoreToken;
      }
    }

    public isSSHSetupCompleteForUserComputeResourcePreference_args deepCopy() {
      return new isSSHSetupCompleteForUserComputeResourcePreference_args(this);
    }

    @Override
    public void clear() {
      this.authzToken = null;
      this.computeResourceId = null;
      this.airavataCredStoreToken = null;
    }

    public org.apache.airavata.model.security.AuthzToken getAuthzToken() {
      return this.authzToken;
    }

    public isSSHSetupCompleteForUserComputeResourcePreference_args setAuthzToken(org.apache.airavata.model.security.AuthzToken authzToken) {
      this.authzToken = authzToken;
      return this;
    }

    public void unsetAuthzToken() {
      this.authzToken = null;
    }

    /** Returns true if field authzToken is set (has been assigned a value) and false otherwise */
    public boolean isSetAuthzToken() {
      return this.authzToken != null;
    }

    public void setAuthzTokenIsSet(boolean value) {
      if (!value) {
        this.authzToken = null;
      }
    }

    public String getComputeResourceId() {
      return this.computeResourceId;
    }

    public isSSHSetupCompleteForUserComputeResourcePreference_args setComputeResourceId(String computeResourceId) {
      this.computeResourceId = computeResourceId;
      return this;
    }

    public void unsetComputeResourceId() {
      this.computeResourceId = null;
    }

    /** Returns true if field computeResourceId is set (has been assigned a value) and false otherwise */
    public boolean isSetComputeResourceId() {
      return this.computeResourceId != null;
    }

    public void setComputeResourceIdIsSet(boolean value) {
      if (!value) {
        this.computeResourceId = null;
      }
    }

    public String getAiravataCredStoreToken() {
      return this.airavataCredStoreToken;
    }

    public isSSHSetupCompleteForUserComputeResourcePreference_args setAiravataCredStoreToken(String airavataCredStoreToken) {
      this.airavataCredStoreToken = airavataCredStoreToken;
      return this;
    }

    public void unsetAiravataCredStoreToken() {
      this.airavataCredStoreToken = null;
    }

    /** Returns true if field airavataCredStoreToken is set (has been assigned a value) and false otherwise */
    public boolean isSetAiravataCredStoreToken() {
      return this.airavataCredStoreToken != null;
    }

    public void setAiravataCredStoreTokenIsSet(boolean value) {
      if (!value) {
        this.airavataCredStoreToken = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case AUTHZ_TOKEN:
        if (value == null) {
          unsetAuthzToken();
        } else {
          setAuthzToken((org.apache.airavata.model.security.AuthzToken)value);
        }
        break;

      case COMPUTE_RESOURCE_ID:
        if (value == null) {
          unsetComputeResourceId();
        } else {
          setComputeResourceId((String)value);
        }
        break;

      case AIRAVATA_CRED_STORE_TOKEN:
        if (value == null) {
          unsetAiravataCredStoreToken();
        } else {
          setAiravataCredStoreToken((String)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case AUTHZ_TOKEN:
        return getAuthzToken();

      case COMPUTE_RESOURCE_ID:
        return getComputeResourceId();

      case AIRAVATA_CRED_STORE_TOKEN:
        return getAiravataCredStoreToken();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case AUTHZ_TOKEN:
        return isSetAuthzToken();
      case COMPUTE_RESOURCE_ID:
        return isSetComputeResourceId();
      case AIRAVATA_CRED_STORE_TOKEN:
        return isSetAiravataCredStoreToken();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof isSSHSetupCompleteForUserComputeResourcePreference_args)
        return this.equals((isSSHSetupCompleteForUserComputeResourcePreference_args)that);
      return false;
    }

    public boolean equals(isSSHSetupCompleteForUserComputeResourcePreference_args that) {
      if (that == null)
        return false;

      boolean this_present_authzToken = true && this.isSetAuthzToken();
      boolean that_present_authzToken = true && that.isSetAuthzToken();
      if (this_present_authzToken || that_present_authzToken) {
        if (!(this_present_authzToken && that_present_authzToken))
          return false;
        if (!this.authzToken.equals(that.authzToken))
          return false;
      }

      boolean this_present_computeResourceId = true && this.isSetComputeResourceId();
      boolean that_present_computeResourceId = true && that.isSetComputeResourceId();
      if (this_present_computeResourceId || that_present_computeResourceId) {
        if (!(this_present_computeResourceId && that_present_computeResourceId))
          return false;
        if (!this.computeResourceId.equals(that.computeResourceId))
          return false;
      }

      boolean this_present_airavataCredStoreToken = true && this.isSetAiravataCredStoreToken();
      boolean that_present_airavataCredStoreToken = true && that.isSetAiravataCredStoreToken();
      if (this_present_airavataCredStoreToken || that_present_airavataCredStoreToken) {
        if (!(this_present_airavataCredStoreToken && that_present_airavataCredStoreToken))
          return false;
        if (!this.airavataCredStoreToken.equals(that.airavataCredStoreToken))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_authzToken = true && (isSetAuthzToken());
      list.add(present_authzToken);
      if (present_authzToken)
        list.add(authzToken);

      boolean present_computeResourceId = true && (isSetComputeResourceId());
      list.add(present_computeResourceId);
      if (present_computeResourceId)
        list.add(computeResourceId);

      boolean present_airavataCredStoreToken = true && (isSetAiravataCredStoreToken());
      list.add(present_airavataCredStoreToken);
      if (present_airavataCredStoreToken)
        list.add(airavataCredStoreToken);

      return list.hashCode();
    }

    @Override
    public int compareTo(isSSHSetupCompleteForUserComputeResourcePreference_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetAuthzToken()).compareTo(other.isSetAuthzToken());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetAuthzToken()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.authzToken, other.authzToken);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetComputeResourceId()).compareTo(other.isSetComputeResourceId());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetComputeResourceId()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.computeResourceId, other.computeResourceId);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetAiravataCredStoreToken()).compareTo(other.isSetAiravataCredStoreToken());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetAiravataCredStoreToken()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.airavataCredStoreToken, other.airavataCredStoreToken);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("isSSHSetupCompleteForUserComputeResourcePreference_args(");
      boolean first = true;

      sb.append("authzToken:");
      if (this.authzToken == null) {
        sb.append("null");
      } else {
        sb.append(this.authzToken);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("computeResourceId:");
      if (this.computeResourceId == null) {
        sb.append("null");
      } else {
        sb.append(this.computeResourceId);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("airavataCredStoreToken:");
      if (this.airavataCredStoreToken == null) {
        sb.append("null");
      } else {
        sb.append(this.airavataCredStoreToken);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      if (authzToken == null) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'authzToken' was not present! Struct: " + toString());
      }
      if (computeResourceId == null) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'computeResourceId' was not present! Struct: " + toString());
      }
      if (airavataCredStoreToken == null) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'airavataCredStoreToken' was not present! Struct: " + toString());
      }
      // check for sub-struct validity
      if (authzToken != null) {
        authzToken.validate();
      }
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class isSSHSetupCompleteForUserComputeResourcePreference_argsStandardSchemeFactory implements SchemeFactory {
      public isSSHSetupCompleteForUserComputeResourcePreference_argsStandardScheme getScheme() {
        return new isSSHSetupCompleteForUserComputeResourcePreference_argsStandardScheme();
      }
    }

    private static class isSSHSetupCompleteForUserComputeResourcePreference_argsStandardScheme extends StandardScheme<isSSHSetupCompleteForUserComputeResourcePreference_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, isSSHSetupCompleteForUserComputeResourcePreference_args struct) throws org.apache.thrift.TException {
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
            case 2: // COMPUTE_RESOURCE_ID
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.computeResourceId = iprot.readString();
                struct.setComputeResourceIdIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 3: // AIRAVATA_CRED_STORE_TOKEN
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.airavataCredStoreToken = iprot.readString();
                struct.setAiravataCredStoreTokenIsSet(true);
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, isSSHSetupCompleteForUserComputeResourcePreference_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.authzToken != null) {
          oprot.writeFieldBegin(AUTHZ_TOKEN_FIELD_DESC);
          struct.authzToken.write(oprot);
          oprot.writeFieldEnd();
        }
        if (struct.computeResourceId != null) {
          oprot.writeFieldBegin(COMPUTE_RESOURCE_ID_FIELD_DESC);
          oprot.writeString(struct.computeResourceId);
          oprot.writeFieldEnd();
        }
        if (struct.airavataCredStoreToken != null) {
          oprot.writeFieldBegin(AIRAVATA_CRED_STORE_TOKEN_FIELD_DESC);
          oprot.writeString(struct.airavataCredStoreToken);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class isSSHSetupCompleteForUserComputeResourcePreference_argsTupleSchemeFactory implements SchemeFactory {
      public isSSHSetupCompleteForUserComputeResourcePreference_argsTupleScheme getScheme() {
        return new isSSHSetupCompleteForUserComputeResourcePreference_argsTupleScheme();
      }
    }

    private static class isSSHSetupCompleteForUserComputeResourcePreference_argsTupleScheme extends TupleScheme<isSSHSetupCompleteForUserComputeResourcePreference_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, isSSHSetupCompleteForUserComputeResourcePreference_args struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        struct.authzToken.write(oprot);
        oprot.writeString(struct.computeResourceId);
        oprot.writeString(struct.airavataCredStoreToken);
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, isSSHSetupCompleteForUserComputeResourcePreference_args struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        struct.authzToken = new org.apache.airavata.model.security.AuthzToken();
        struct.authzToken.read(iprot);
        struct.setAuthzTokenIsSet(true);
        struct.computeResourceId = iprot.readString();
        struct.setComputeResourceIdIsSet(true);
        struct.airavataCredStoreToken = iprot.readString();
        struct.setAiravataCredStoreTokenIsSet(true);
      }
    }

  }
