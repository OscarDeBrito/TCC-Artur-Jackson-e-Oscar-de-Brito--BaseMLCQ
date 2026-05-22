public static class beginBlobDownload_args implements org.apache.storm.thrift.TBase<beginBlobDownload_args, beginBlobDownload_args._Fields>, java.io.Serializable, Cloneable, Comparable<beginBlobDownload_args>   {
    private static final org.apache.storm.thrift.protocol.TStruct STRUCT_DESC = new org.apache.storm.thrift.protocol.TStruct("beginBlobDownload_args");

    private static final org.apache.storm.thrift.protocol.TField KEY_FIELD_DESC = new org.apache.storm.thrift.protocol.TField("key", org.apache.storm.thrift.protocol.TType.STRING, (short)1);

    private static final org.apache.storm.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new beginBlobDownload_argsStandardSchemeFactory();
    private static final org.apache.storm.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new beginBlobDownload_argsTupleSchemeFactory();

    private @org.apache.storm.thrift.annotation.Nullable java.lang.String key; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.storm.thrift.TFieldIdEnum {
      KEY((short)1, "key");

      private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

      static {
        for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      @org.apache.storm.thrift.annotation.Nullable
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 1: // KEY
            return KEY;
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
      @org.apache.storm.thrift.annotation.Nullable
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
    public static final java.util.Map<_Fields, org.apache.storm.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      java.util.Map<_Fields, org.apache.storm.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.storm.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.KEY, new org.apache.storm.thrift.meta_data.FieldMetaData("key", org.apache.storm.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.storm.thrift.meta_data.FieldValueMetaData(org.apache.storm.thrift.protocol.TType.STRING)));
      metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
      org.apache.storm.thrift.meta_data.FieldMetaData.addStructMetaDataMap(beginBlobDownload_args.class, metaDataMap);
    }

    public beginBlobDownload_args() {
    }

    public beginBlobDownload_args(
      java.lang.String key)
    {
      this();
      this.key = key;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public beginBlobDownload_args(beginBlobDownload_args other) {
      if (other.is_set_key()) {
        this.key = other.key;
      }
    }

    public beginBlobDownload_args deepCopy() {
      return new beginBlobDownload_args(this);
    }

    @Override
    public void clear() {
      this.key = null;
    }

    @org.apache.storm.thrift.annotation.Nullable
    public java.lang.String get_key() {
      return this.key;
    }

    public void set_key(@org.apache.storm.thrift.annotation.Nullable java.lang.String key) {
      this.key = key;
    }

    public void unset_key() {
      this.key = null;
    }

    /** Returns true if field key is set (has been assigned a value) and false otherwise */
    public boolean is_set_key() {
      return this.key != null;
    }

    public void set_key_isSet(boolean value) {
      if (!value) {
        this.key = null;
      }
    }

    public void setFieldValue(_Fields field, @org.apache.storm.thrift.annotation.Nullable java.lang.Object value) {
      switch (field) {
      case KEY:
        if (value == null) {
          unset_key();
        } else {
          set_key((java.lang.String)value);
        }
        break;

      }
    }

    @org.apache.storm.thrift.annotation.Nullable
    public java.lang.Object getFieldValue(_Fields field) {
      switch (field) {
      case KEY:
        return get_key();

      }
      throw new java.lang.IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new java.lang.IllegalArgumentException();
      }

      switch (field) {
      case KEY:
        return is_set_key();
      }
      throw new java.lang.IllegalStateException();
    }

    @Override
    public boolean equals(java.lang.Object that) {
      if (that == null)
        return false;
      if (that instanceof beginBlobDownload_args)
        return this.equals((beginBlobDownload_args)that);
      return false;
    }

    public boolean equals(beginBlobDownload_args that) {
      if (that == null)
        return false;
      if (this == that)
        return true;

      boolean this_present_key = true && this.is_set_key();
      boolean that_present_key = true && that.is_set_key();
      if (this_present_key || that_present_key) {
        if (!(this_present_key && that_present_key))
          return false;
        if (!this.key.equals(that.key))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int hashCode = 1;

      hashCode = hashCode * 8191 + ((is_set_key()) ? 131071 : 524287);
      if (is_set_key())
        hashCode = hashCode * 8191 + key.hashCode();

      return hashCode;
    }

    @Override
    public int compareTo(beginBlobDownload_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = java.lang.Boolean.valueOf(is_set_key()).compareTo(other.is_set_key());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (is_set_key()) {
        lastComparison = org.apache.storm.thrift.TBaseHelper.compareTo(this.key, other.key);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    @org.apache.storm.thrift.annotation.Nullable
    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.storm.thrift.protocol.TProtocol iprot) throws org.apache.storm.thrift.TException {
      scheme(iprot).read(iprot, this);
    }

    public void write(org.apache.storm.thrift.protocol.TProtocol oprot) throws org.apache.storm.thrift.TException {
      scheme(oprot).write(oprot, this);
    }

    @Override
    public java.lang.String toString() {
      java.lang.StringBuilder sb = new java.lang.StringBuilder("beginBlobDownload_args(");
      boolean first = true;

      sb.append("key:");
      if (this.key == null) {
        sb.append("null");
      } else {
        sb.append(this.key);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.storm.thrift.TException {
      // check for required fields
      // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.storm.thrift.protocol.TCompactProtocol(new org.apache.storm.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.storm.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
      try {
        read(new org.apache.storm.thrift.protocol.TCompactProtocol(new org.apache.storm.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.storm.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class beginBlobDownload_argsStandardSchemeFactory implements org.apache.storm.thrift.scheme.SchemeFactory {
      public beginBlobDownload_argsStandardScheme getScheme() {
        return new beginBlobDownload_argsStandardScheme();
      }
    }

    private static class beginBlobDownload_argsStandardScheme extends org.apache.storm.thrift.scheme.StandardScheme<beginBlobDownload_args> {

      public void read(org.apache.storm.thrift.protocol.TProtocol iprot, beginBlobDownload_args struct) throws org.apache.storm.thrift.TException {
        org.apache.storm.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.storm.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // KEY
              if (schemeField.type == org.apache.storm.thrift.protocol.TType.STRING) {
                struct.key = iprot.readString();
                struct.set_key_isSet(true);
              } else { 
                org.apache.storm.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.storm.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();
        struct.validate();
      }

      public void write(org.apache.storm.thrift.protocol.TProtocol oprot, beginBlobDownload_args struct) throws org.apache.storm.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.key != null) {
          oprot.writeFieldBegin(KEY_FIELD_DESC);
          oprot.writeString(struct.key);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class beginBlobDownload_argsTupleSchemeFactory implements org.apache.storm.thrift.scheme.SchemeFactory {
      public beginBlobDownload_argsTupleScheme getScheme() {
        return new beginBlobDownload_argsTupleScheme();
      }
    }

    private static class beginBlobDownload_argsTupleScheme extends org.apache.storm.thrift.scheme.TupleScheme<beginBlobDownload_args> {

      @Override
      public void write(org.apache.storm.thrift.protocol.TProtocol prot, beginBlobDownload_args struct) throws org.apache.storm.thrift.TException {
        org.apache.storm.thrift.protocol.TTupleProtocol oprot = (org.apache.storm.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet optionals = new java.util.BitSet();
        if (struct.is_set_key()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.is_set_key()) {
          oprot.writeString(struct.key);
        }
      }

      @Override
      public void read(org.apache.storm.thrift.protocol.TProtocol prot, beginBlobDownload_args struct) throws org.apache.storm.thrift.TException {
        org.apache.storm.thrift.protocol.TTupleProtocol iprot = (org.apache.storm.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          struct.key = iprot.readString();
          struct.set_key_isSet(true);
        }
      }
    }

    private static <S extends org.apache.storm.thrift.scheme.IScheme> S scheme(org.apache.storm.thrift.protocol.TProtocol proto) {
      return (org.apache.storm.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
    }
  }
