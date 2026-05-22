public static class createExperiment_result implements org.apache.thrift.TBase<createExperiment_result, createExperiment_result._Fields>, java.io.Serializable, Cloneable, Comparable<createExperiment_result>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("createExperiment_result");

    private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("success", org.apache.thrift.protocol.TType.STRING, (short)0);
    private static final org.apache.thrift.protocol.TField IRE_FIELD_DESC = new org.apache.thrift.protocol.TField("ire", org.apache.thrift.protocol.TType.STRUCT, (short)1);
    private static final org.apache.thrift.protocol.TField ACE_FIELD_DESC = new org.apache.thrift.protocol.TField("ace", org.apache.thrift.protocol.TType.STRUCT, (short)2);
    private static final org.apache.thrift.protocol.TField ASE_FIELD_DESC = new org.apache.thrift.protocol.TField("ase", org.apache.thrift.protocol.TType.STRUCT, (short)3);
    private static final org.apache.thrift.protocol.TField AE_FIELD_DESC = new org.apache.thrift.protocol.TField("ae", org.apache.thrift.protocol.TType.STRUCT, (short)4);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new createExperiment_resultStandardSchemeFactory());
      schemes.put(TupleScheme.class, new createExperiment_resultTupleSchemeFactory());
    }

    public String success; // required
    public org.apache.airavata.model.error.InvalidRequestException ire; // required
    public org.apache.airavata.model.error.AiravataClientException ace; // required
    public org.apache.airavata.model.error.AiravataSystemException ase; // required
    public org.apache.airavata.model.error.AuthorizationException ae; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      SUCCESS((short)0, "success"),
      IRE((short)1, "ire"),
      ACE((short)2, "ace"),
      ASE((short)3, "ase"),
      AE((short)4, "ae");

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
          case 0: // SUCCESS
            return SUCCESS;
          case 1: // IRE
            return IRE;
          case 2: // ACE
            return ACE;
          case 3: // ASE
            return ASE;
          case 4: // AE
            return AE;
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
      tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
      tmpMap.put(_Fields.IRE, new org.apache.thrift.meta_data.FieldMetaData("ire", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT)));
      tmpMap.put(_Fields.ACE, new org.apache.thrift.meta_data.FieldMetaData("ace", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT)));
      tmpMap.put(_Fields.ASE, new org.apache.thrift.meta_data.FieldMetaData("ase", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT)));
      tmpMap.put(_Fields.AE, new org.apache.thrift.meta_data.FieldMetaData("ae", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(createExperiment_result.class, metaDataMap);
    }

    public createExperiment_result() {
    }

    public createExperiment_result(
      String success,
      org.apache.airavata.model.error.InvalidRequestException ire,
      org.apache.airavata.model.error.AiravataClientException ace,
      org.apache.airavata.model.error.AiravataSystemException ase,
      org.apache.airavata.model.error.AuthorizationException ae)
    {
      this();
      this.success = success;
      this.ire = ire;
      this.ace = ace;
      this.ase = ase;
      this.ae = ae;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public createExperiment_result(createExperiment_result other) {
      if (other.isSetSuccess()) {
        this.success = other.success;
      }
      if (other.isSetIre()) {
        this.ire = new org.apache.airavata.model.error.InvalidRequestException(other.ire);
      }
      if (other.isSetAce()) {
        this.ace = new org.apache.airavata.model.error.AiravataClientException(other.ace);
      }
      if (other.isSetAse()) {
        this.ase = new org.apache.airavata.model.error.AiravataSystemException(other.ase);
      }
      if (other.isSetAe()) {
        this.ae = new org.apache.airavata.model.error.AuthorizationException(other.ae);
      }
    }

    public createExperiment_result deepCopy() {
      return new createExperiment_result(this);
    }

    @Override
    public void clear() {
      this.success = null;
      this.ire = null;
      this.ace = null;
      this.ase = null;
      this.ae = null;
    }

    public String getSuccess() {
      return this.success;
    }

    public createExperiment_result setSuccess(String success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been assigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public org.apache.airavata.model.error.InvalidRequestException getIre() {
      return this.ire;
    }

    public createExperiment_result setIre(org.apache.airavata.model.error.InvalidRequestException ire) {
      this.ire = ire;
      return this;
    }

    public void unsetIre() {
      this.ire = null;
    }

    /** Returns true if field ire is set (has been assigned a value) and false otherwise */
    public boolean isSetIre() {
      return this.ire != null;
    }

    public void setIreIsSet(boolean value) {
      if (!value) {
        this.ire = null;
      }
    }

    public org.apache.airavata.model.error.AiravataClientException getAce() {
      return this.ace;
    }

    public createExperiment_result setAce(org.apache.airavata.model.error.AiravataClientException ace) {
      this.ace = ace;
      return this;
    }

    public void unsetAce() {
      this.ace = null;
    }

    /** Returns true if field ace is set (has been assigned a value) and false otherwise */
    public boolean isSetAce() {
      return this.ace != null;
    }

    public void setAceIsSet(boolean value) {
      if (!value) {
        this.ace = null;
      }
    }

    public org.apache.airavata.model.error.AiravataSystemException getAse() {
      return this.ase;
    }

    public createExperiment_result setAse(org.apache.airavata.model.error.AiravataSystemException ase) {
      this.ase = ase;
      return this;
    }

    public void unsetAse() {
      this.ase = null;
    }

    /** Returns true if field ase is set (has been assigned a value) and false otherwise */
    public boolean isSetAse() {
      return this.ase != null;
    }

    public void setAseIsSet(boolean value) {
      if (!value) {
        this.ase = null;
      }
    }

    public org.apache.airavata.model.error.AuthorizationException getAe() {
      return this.ae;
    }

    public createExperiment_result setAe(org.apache.airavata.model.error.AuthorizationException ae) {
      this.ae = ae;
      return this;
    }

    public void unsetAe() {
      this.ae = null;
    }

    /** Returns true if field ae is set (has been assigned a value) and false otherwise */
    public boolean isSetAe() {
      return this.ae != null;
    }

    public void setAeIsSet(boolean value) {
      if (!value) {
        this.ae = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((String)value);
        }
        break;

      case IRE:
        if (value == null) {
          unsetIre();
        } else {
          setIre((org.apache.airavata.model.error.InvalidRequestException)value);
        }
        break;

      case ACE:
        if (value == null) {
          unsetAce();
        } else {
          setAce((org.apache.airavata.model.error.AiravataClientException)value);
        }
        break;

      case ASE:
        if (value == null) {
          unsetAse();
        } else {
          setAse((org.apache.airavata.model.error.AiravataSystemException)value);
        }
        break;

      case AE:
        if (value == null) {
          unsetAe();
        } else {
          setAe((org.apache.airavata.model.error.AuthorizationException)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case IRE:
        return getIre();

      case ACE:
        return getAce();

      case ASE:
        return getAse();

      case AE:
        return getAe();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case IRE:
        return isSetIre();
      case ACE:
        return isSetAce();
      case ASE:
        return isSetAse();
      case AE:
        return isSetAe();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof createExperiment_result)
        return this.equals((createExperiment_result)that);
      return false;
    }

    public boolean equals(createExperiment_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_ire = true && this.isSetIre();
      boolean that_present_ire = true && that.isSetIre();
      if (this_present_ire || that_present_ire) {
        if (!(this_present_ire && that_present_ire))
          return false;
        if (!this.ire.equals(that.ire))
          return false;
      }

      boolean this_present_ace = true && this.isSetAce();
      boolean that_present_ace = true && that.isSetAce();
      if (this_present_ace || that_present_ace) {
        if (!(this_present_ace && that_present_ace))
          return false;
        if (!this.ace.equals(that.ace))
          return false;
      }

      boolean this_present_ase = true && this.isSetAse();
      boolean that_present_ase = true && that.isSetAse();
      if (this_present_ase || that_present_ase) {
        if (!(this_present_ase && that_present_ase))
          return false;
        if (!this.ase.equals(that.ase))
          return false;
      }

      boolean this_present_ae = true && this.isSetAe();
      boolean that_present_ae = true && that.isSetAe();
      if (this_present_ae || that_present_ae) {
        if (!(this_present_ae && that_present_ae))
          return false;
        if (!this.ae.equals(that.ae))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_success = true && (isSetSuccess());
      list.add(present_success);
      if (present_success)
        list.add(success);

      boolean present_ire = true && (isSetIre());
      list.add(present_ire);
      if (present_ire)
        list.add(ire);

      boolean present_ace = true && (isSetAce());
      list.add(present_ace);
      if (present_ace)
        list.add(ace);

      boolean present_ase = true && (isSetAse());
      list.add(present_ase);
      if (present_ase)
        list.add(ase);

      boolean present_ae = true && (isSetAe());
      list.add(present_ae);
      if (present_ae)
        list.add(ae);

      return list.hashCode();
    }

    @Override
    public int compareTo(createExperiment_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(other.isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetSuccess()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, other.success);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetIre()).compareTo(other.isSetIre());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetIre()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ire, other.ire);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetAce()).compareTo(other.isSetAce());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetAce()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ace, other.ace);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetAse()).compareTo(other.isSetAse());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetAse()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ase, other.ase);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetAe()).compareTo(other.isSetAe());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetAe()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ae, other.ae);
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
      StringBuilder sb = new StringBuilder("createExperiment_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("ire:");
      if (this.ire == null) {
        sb.append("null");
      } else {
        sb.append(this.ire);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("ace:");
      if (this.ace == null) {
        sb.append("null");
      } else {
        sb.append(this.ace);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("ase:");
      if (this.ase == null) {
        sb.append("null");
      } else {
        sb.append(this.ase);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("ae:");
      if (this.ae == null) {
        sb.append("null");
      } else {
        sb.append(this.ae);
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

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class createExperiment_resultStandardSchemeFactory implements SchemeFactory {
      public createExperiment_resultStandardScheme getScheme() {
        return new createExperiment_resultStandardScheme();
      }
    }

    private static class createExperiment_resultStandardScheme extends StandardScheme<createExperiment_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, createExperiment_result struct) throws org.apache.thrift.TException {
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
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.success = iprot.readString();
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, createExperiment_result struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.success != null) {
          oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
          oprot.writeString(struct.success);
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

    private static class createExperiment_resultTupleSchemeFactory implements SchemeFactory {
      public createExperiment_resultTupleScheme getScheme() {
        return new createExperiment_resultTupleScheme();
      }
    }

    private static class createExperiment_resultTupleScheme extends TupleScheme<createExperiment_result> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, createExperiment_result struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetSuccess()) {
          optionals.set(0);
        }
        if (struct.isSetIre()) {
          optionals.set(1);
        }
        if (struct.isSetAce()) {
          optionals.set(2);
        }
        if (struct.isSetAse()) {
          optionals.set(3);
        }
        if (struct.isSetAe()) {
          optionals.set(4);
        }
        oprot.writeBitSet(optionals, 5);
        if (struct.isSetSuccess()) {
          oprot.writeString(struct.success);
        }
        if (struct.isSetIre()) {
          struct.ire.write(oprot);
        }
        if (struct.isSetAce()) {
          struct.ace.write(oprot);
        }
        if (struct.isSetAse()) {
          struct.ase.write(oprot);
        }
        if (struct.isSetAe()) {
          struct.ae.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, createExperiment_result struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(5);
        if (incoming.get(0)) {
          struct.success = iprot.readString();
          struct.setSuccessIsSet(true);
        }
        if (incoming.get(1)) {
          struct.ire = new org.apache.airavata.model.error.InvalidRequestException();
          struct.ire.read(iprot);
          struct.setIreIsSet(true);
        }
        if (incoming.get(2)) {
          struct.ace = new org.apache.airavata.model.error.AiravataClientException();
          struct.ace.read(iprot);
          struct.setAceIsSet(true);
        }
        if (incoming.get(3)) {
          struct.ase = new org.apache.airavata.model.error.AiravataSystemException();
          struct.ase.read(iprot);
          struct.setAseIsSet(true);
        }
        if (incoming.get(4)) {
          struct.ae = new org.apache.airavata.model.error.AuthorizationException();
          struct.ae.read(iprot);
          struct.setAeIsSet(true);
        }
      }
    }

  }
