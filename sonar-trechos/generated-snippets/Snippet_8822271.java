public static class getAllApplicationInterfaceNames_args implements org.apache.thrift.TBase<getAllApplicationInterfaceNames_args, getAllApplicationInterfaceNames_args._Fields>, java.io.Serializable, Cloneable, Comparable<getAllApplicationInterfaceNames_args>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("getAllApplicationInterfaceNames_args");

    private static final org.apache.thrift.protocol.TField GATEWAY_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("gatewayId", org.apache.thrift.protocol.TType.STRING, (short)1);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new getAllApplicationInterfaceNames_argsStandardSchemeFactory());
      schemes.put(TupleScheme.class, new getAllApplicationInterfaceNames_argsTupleSchemeFactory());
    }

    public String gatewayId; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      GATEWAY_ID((short)1, "gatewayId");

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
          case 1: // GATEWAY_ID
            return GATEWAY_ID;
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
      tmpMap.put(_Fields.GATEWAY_ID, new org.apache.thrift.meta_data.FieldMetaData("gatewayId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(getAllApplicationInterfaceNames_args.class, metaDataMap);
    }

    public getAllApplicationInterfaceNames_args() {
    }

    public getAllApplicationInterfaceNames_args(
      String gatewayId)
    {
      this();
      this.gatewayId = gatewayId;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getAllApplicationInterfaceNames_args(getAllApplicationInterfaceNames_args other) {
      if (other.isSetGatewayId()) {
        this.gatewayId = other.gatewayId;
      }
    }

    public getAllApplicationInterfaceNames_args deepCopy() {
      return new getAllApplicationInterfaceNames_args(this);
    }

    @Override
    public void clear() {
      this.gatewayId = null;
    }

    public String getGatewayId() {
      return this.gatewayId;
    }

    public getAllApplicationInterfaceNames_args setGatewayId(String gatewayId) {
      this.gatewayId = gatewayId;
      return this;
    }

    public void unsetGatewayId() {
      this.gatewayId = null;
    }

    /** Returns true if field gatewayId is set (has been assigned a value) and false otherwise */
    public boolean isSetGatewayId() {
      return this.gatewayId != null;
    }

    public void setGatewayIdIsSet(boolean value) {
      if (!value) {
        this.gatewayId = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case GATEWAY_ID:
        if (value == null) {
          unsetGatewayId();
        } else {
          setGatewayId((String)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case GATEWAY_ID:
        return getGatewayId();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case GATEWAY_ID:
        return isSetGatewayId();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getAllApplicationInterfaceNames_args)
        return this.equals((getAllApplicationInterfaceNames_args)that);
      return false;
    }

    public boolean equals(getAllApplicationInterfaceNames_args that) {
      if (that == null)
        return false;

      boolean this_present_gatewayId = true && this.isSetGatewayId();
      boolean that_present_gatewayId = true && that.isSetGatewayId();
      if (this_present_gatewayId || that_present_gatewayId) {
        if (!(this_present_gatewayId && that_present_gatewayId))
          return false;
        if (!this.gatewayId.equals(that.gatewayId))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_gatewayId = true && (isSetGatewayId());
      list.add(present_gatewayId);
      if (present_gatewayId)
        list.add(gatewayId);

      return list.hashCode();
    }

    @Override
    public int compareTo(getAllApplicationInterfaceNames_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetGatewayId()).compareTo(other.isSetGatewayId());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetGatewayId()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.gatewayId, other.gatewayId);
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
      StringBuilder sb = new StringBuilder("getAllApplicationInterfaceNames_args(");
      boolean first = true;

      sb.append("gatewayId:");
      if (this.gatewayId == null) {
        sb.append("null");
      } else {
        sb.append(this.gatewayId);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      if (gatewayId == null) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'gatewayId' was not present! Struct: " + toString());
      }
      // check for sub-struct validity
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

    private static class getAllApplicationInterfaceNames_argsStandardSchemeFactory implements SchemeFactory {
      public getAllApplicationInterfaceNames_argsStandardScheme getScheme() {
        return new getAllApplicationInterfaceNames_argsStandardScheme();
      }
    }

    private static class getAllApplicationInterfaceNames_argsStandardScheme extends StandardScheme<getAllApplicationInterfaceNames_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, getAllApplicationInterfaceNames_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // GATEWAY_ID
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.gatewayId = iprot.readString();
                struct.setGatewayIdIsSet(true);
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, getAllApplicationInterfaceNames_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.gatewayId != null) {
          oprot.writeFieldBegin(GATEWAY_ID_FIELD_DESC);
          oprot.writeString(struct.gatewayId);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class getAllApplicationInterfaceNames_argsTupleSchemeFactory implements SchemeFactory {
      public getAllApplicationInterfaceNames_argsTupleScheme getScheme() {
        return new getAllApplicationInterfaceNames_argsTupleScheme();
      }
    }

    private static class getAllApplicationInterfaceNames_argsTupleScheme extends TupleScheme<getAllApplicationInterfaceNames_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, getAllApplicationInterfaceNames_args struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        oprot.writeString(struct.gatewayId);
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, getAllApplicationInterfaceNames_args struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        struct.gatewayId = iprot.readString();
        struct.setGatewayIdIsSet(true);
      }
    }

  }
