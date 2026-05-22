public static class deleteTable_args implements org.apache.thrift.TBase<deleteTable_args, deleteTable_args._Fields>, java.io.Serializable, Cloneable, Comparable<deleteTable_args>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("deleteTable_args");

    private static final org.apache.thrift.protocol.TField LOGIN_FIELD_DESC = new org.apache.thrift.protocol.TField("login", org.apache.thrift.protocol.TType.STRING, (short)1);
    private static final org.apache.thrift.protocol.TField TABLE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("tableName", org.apache.thrift.protocol.TType.STRING, (short)2);

    private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new deleteTable_argsStandardSchemeFactory();
    private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new deleteTable_argsTupleSchemeFactory();

    public @org.apache.thrift.annotation.Nullable java.nio.ByteBuffer login; // required
    public @org.apache.thrift.annotation.Nullable java.lang.String tableName; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      LOGIN((short)1, "login"),
      TABLE_NAME((short)2, "tableName");

      private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

      static {
        for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      @org.apache.thrift.annotation.Nullable
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 1: // LOGIN
            return LOGIN;
          case 2: // TABLE_NAME
            return TABLE_NAME;
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
        if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      @org.apache.thrift.annotation.Nullable
      public static _Fields findByName(java.lang.String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final java.lang.String _fieldName;

      _Fields(short thriftId, java.lang.String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public java.lang.String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.LOGIN, new org.apache.thrift.meta_data.FieldMetaData("login", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING          , true)));
      tmpMap.put(_Fields.TABLE_NAME, new org.apache.thrift.meta_data.FieldMetaData("tableName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
      metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(deleteTable_args.class, metaDataMap);
    }

    public deleteTable_args() {
    }

    public deleteTable_args(
      java.nio.ByteBuffer login,
      java.lang.String tableName)
    {
      this();
      this.login = org.apache.thrift.TBaseHelper.copyBinary(login);
      this.tableName = tableName;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public deleteTable_args(deleteTable_args other) {
      if (other.isSetLogin()) {
        this.login = org.apache.thrift.TBaseHelper.copyBinary(other.login);
      }
      if (other.isSetTableName()) {
        this.tableName = other.tableName;
      }
    }

    public deleteTable_args deepCopy() {
      return new deleteTable_args(this);
    }

    @Override
    public void clear() {
      this.login = null;
      this.tableName = null;
    }

    public byte[] getLogin() {
      setLogin(org.apache.thrift.TBaseHelper.rightSize(login));
      return login == null ? null : login.array();
    }

    public java.nio.ByteBuffer bufferForLogin() {
      return org.apache.thrift.TBaseHelper.copyBinary(login);
    }

    public deleteTable_args setLogin(byte[] login) {
      this.login = login == null ? (java.nio.ByteBuffer)null     : java.nio.ByteBuffer.wrap(login.clone());
      return this;
    }

    public deleteTable_args setLogin(@org.apache.thrift.annotation.Nullable java.nio.ByteBuffer login) {
      this.login = org.apache.thrift.TBaseHelper.copyBinary(login);
      return this;
    }

    public void unsetLogin() {
      this.login = null;
    }

    /** Returns true if field login is set (has been assigned a value) and false otherwise */
    public boolean isSetLogin() {
      return this.login != null;
    }

    public void setLoginIsSet(boolean value) {
      if (!value) {
        this.login = null;
      }
    }

    @org.apache.thrift.annotation.Nullable
    public java.lang.String getTableName() {
      return this.tableName;
    }

    public deleteTable_args setTableName(@org.apache.thrift.annotation.Nullable java.lang.String tableName) {
      this.tableName = tableName;
      return this;
    }

    public void unsetTableName() {
      this.tableName = null;
    }

    /** Returns true if field tableName is set (has been assigned a value) and false otherwise */
    public boolean isSetTableName() {
      return this.tableName != null;
    }

    public void setTableNameIsSet(boolean value) {
      if (!value) {
        this.tableName = null;
      }
    }

    public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
      switch (field) {
      case LOGIN:
        if (value == null) {
          unsetLogin();
        } else {
          if (value instanceof byte[]) {
            setLogin((byte[])value);
          } else {
            setLogin((java.nio.ByteBuffer)value);
          }
        }
        break;

      case TABLE_NAME:
        if (value == null) {
          unsetTableName();
        } else {
          setTableName((java.lang.String)value);
        }
        break;

      }
    }

    @org.apache.thrift.annotation.Nullable
    public java.lang.Object getFieldValue(_Fields field) {
      switch (field) {
      case LOGIN:
        return getLogin();

      case TABLE_NAME:
        return getTableName();

      }
      throw new java.lang.IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new java.lang.IllegalArgumentException();
      }

      switch (field) {
      case LOGIN:
        return isSetLogin();
      case TABLE_NAME:
        return isSetTableName();
      }
      throw new java.lang.IllegalStateException();
    }

    @Override
    public boolean equals(java.lang.Object that) {
      if (that == null)
        return false;
      if (that instanceof deleteTable_args)
        return this.equals((deleteTable_args)that);
      return false;
    }

    public boolean equals(deleteTable_args that) {
      if (that == null)
        return false;
      if (this == that)
        return true;

      boolean this_present_login = true && this.isSetLogin();
      boolean that_present_login = true && that.isSetLogin();
      if (this_present_login || that_present_login) {
        if (!(this_present_login && that_present_login))
          return false;
        if (!this.login.equals(that.login))
          return false;
      }

      boolean this_present_tableName = true && this.isSetTableName();
      boolean that_present_tableName = true && that.isSetTableName();
      if (this_present_tableName || that_present_tableName) {
        if (!(this_present_tableName && that_present_tableName))
          return false;
        if (!this.tableName.equals(that.tableName))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int hashCode = 1;

      hashCode = hashCode * 8191 + ((isSetLogin()) ? 131071 : 524287);
      if (isSetLogin())
        hashCode = hashCode * 8191 + login.hashCode();

      hashCode = hashCode * 8191 + ((isSetTableName()) ? 131071 : 524287);
      if (isSetTableName())
        hashCode = hashCode * 8191 + tableName.hashCode();

      return hashCode;
    }

    @Override
    public int compareTo(deleteTable_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = java.lang.Boolean.valueOf(isSetLogin()).compareTo(other.isSetLogin());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetLogin()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.login, other.login);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = java.lang.Boolean.valueOf(isSetTableName()).compareTo(other.isSetTableName());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetTableName()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tableName, other.tableName);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    @org.apache.thrift.annotation.Nullable
    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      scheme(iprot).read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      scheme(oprot).write(oprot, this);
    }

    @Override
    public java.lang.String toString() {
      java.lang.StringBuilder sb = new java.lang.StringBuilder("deleteTable_args(");
      boolean first = true;

      sb.append("login:");
      if (this.login == null) {
        sb.append("null");
      } else {
        org.apache.thrift.TBaseHelper.toString(this.login, sb);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("tableName:");
      if (this.tableName == null) {
        sb.append("null");
      } else {
        sb.append(this.tableName);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class deleteTable_argsStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
      public deleteTable_argsStandardScheme getScheme() {
        return new deleteTable_argsStandardScheme();
      }
    }

    private static class deleteTable_argsStandardScheme extends org.apache.thrift.scheme.StandardScheme<deleteTable_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, deleteTable_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // LOGIN
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.login = iprot.readBinary();
                struct.setLoginIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 2: // TABLE_NAME
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.tableName = iprot.readString();
                struct.setTableNameIsSet(true);
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, deleteTable_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.login != null) {
          oprot.writeFieldBegin(LOGIN_FIELD_DESC);
          oprot.writeBinary(struct.login);
          oprot.writeFieldEnd();
        }
        if (struct.tableName != null) {
          oprot.writeFieldBegin(TABLE_NAME_FIELD_DESC);
          oprot.writeString(struct.tableName);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class deleteTable_argsTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
      public deleteTable_argsTupleScheme getScheme() {
        return new deleteTable_argsTupleScheme();
      }
    }

    private static class deleteTable_argsTupleScheme extends org.apache.thrift.scheme.TupleScheme<deleteTable_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, deleteTable_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet optionals = new java.util.BitSet();
        if (struct.isSetLogin()) {
          optionals.set(0);
        }
        if (struct.isSetTableName()) {
          optionals.set(1);
        }
        oprot.writeBitSet(optionals, 2);
        if (struct.isSetLogin()) {
          oprot.writeBinary(struct.login);
        }
        if (struct.isSetTableName()) {
          oprot.writeString(struct.tableName);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, deleteTable_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet incoming = iprot.readBitSet(2);
        if (incoming.get(0)) {
          struct.login = iprot.readBinary();
          struct.setLoginIsSet(true);
        }
        if (incoming.get(1)) {
          struct.tableName = iprot.readString();
          struct.setTableNameIsSet(true);
        }
      }
    }

    private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
      return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
    }
  }
