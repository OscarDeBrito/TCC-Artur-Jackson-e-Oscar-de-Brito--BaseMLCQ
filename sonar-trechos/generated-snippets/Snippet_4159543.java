public class Snippet__4159543 {

    public  static final class Signature extends
          com.google.protobuf.GeneratedMessageV3 implements
          // @@protoc_insertion_point(message_implements:Signature)
          SignatureOrBuilder {
      private static final long serialVersionUID = 0L;
        // Use Signature.newBuilder() to construct.
        private Signature(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
          super(builder);
        }
        private Signature() {
          columns_ = java.util.Collections.emptyList();
          sql_ = "";
          parameters_ = java.util.Collections.emptyList();
          statementType_ = 0;
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
          return this.unknownFields;
        }
        private Signature(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
          this();
          if (extensionRegistry == null) {
            throw new java.lang.NullPointerException();
          }
          int mutable_bitField0_ = 0;
          com.google.protobuf.UnknownFieldSet.Builder unknownFields =
              com.google.protobuf.UnknownFieldSet.newBuilder();
          try {
            boolean done = false;
            while (!done) {
              int tag = input.readTag();
              switch (tag) {
                case 0:
                  done = true;
                  break;
                case 10: {
                  if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
                    columns_ = new java.util.ArrayList<org.apache.calcite.avatica.proto.Common.ColumnMetaData>();
                    mutable_bitField0_ |= 0x00000001;
                  }
                  columns_.add(
                      input.readMessage(org.apache.calcite.avatica.proto.Common.ColumnMetaData.parser(), extensionRegistry));
                  break;
                }
                case 18: {
                  java.lang.String s = input.readStringRequireUtf8();

                  sql_ = s;
                  break;
                }
                case 26: {
                  if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
                    parameters_ = new java.util.ArrayList<org.apache.calcite.avatica.proto.Common.AvaticaParameter>();
                    mutable_bitField0_ |= 0x00000004;
                  }
                  parameters_.add(
                      input.readMessage(org.apache.calcite.avatica.proto.Common.AvaticaParameter.parser(), extensionRegistry));
                  break;
                }
                case 34: {
                  org.apache.calcite.avatica.proto.Common.CursorFactory.Builder subBuilder = null;
                  if (cursorFactory_ != null) {
                    subBuilder = cursorFactory_.toBuilder();
                  }
                  cursorFactory_ = input.readMessage(org.apache.calcite.avatica.proto.Common.CursorFactory.parser(), extensionRegistry);
                  if (subBuilder != null) {
                    subBuilder.mergeFrom(cursorFactory_);
                    cursorFactory_ = subBuilder.buildPartial();
                  }

                  break;
                }
                case 40: {
                  int rawValue = input.readEnum();

                  statementType_ = rawValue;
                  break;
                }
                default: {
                  if (!parseUnknownFieldProto3(
                      input, unknownFields, extensionRegistry, tag)) {
                    done = true;
                  }
                  break;
                }
              }
            }
          } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
          } catch (java.io.IOException e) {
            throw new com.google.protobuf.InvalidProtocolBufferException(
                e).setUnfinishedMessage(this);
          } finally {
            if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              columns_ = java.util.Collections.unmodifiableList(columns_);
            }
            if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              parameters_ = java.util.Collections.unmodifiableList(parameters_);
            }
            this.unknownFields = unknownFields.build();
            makeExtensionsImmutable();
          }
        }
        public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
          return org.apache.calcite.avatica.proto.Common.internal_static_Signature_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
          return org.apache.calcite.avatica.proto.Common.internal_static_Signature_fieldAccessorTable
              .ensureFieldAccessorsInitialized(
                  org.apache.calcite.avatica.proto.Common.Signature.class, org.apache.calcite.avatica.proto.Common.Signature.Builder.class);
        }

        private int bitField0_;
        public static final int COLUMNS_FIELD_NUMBER = 1;
        private java.util.List<org.apache.calcite.avatica.proto.Common.ColumnMetaData> columns_;
        /**
         * <code>repeated .ColumnMetaData columns = 1;</code>
         */
        public java.util.List<org.apache.calcite.avatica.proto.Common.ColumnMetaData> getColumnsList() {
          return columns_;
        }
        /**
         * <code>repeated .ColumnMetaData columns = 1;</code>
         */
        public java.util.List<? extends org.apache.calcite.avatica.proto.Common.ColumnMetaDataOrBuilder> 
            getColumnsOrBuilderList() {
          return columns_;
        }
        /**
         * <code>repeated .ColumnMetaData columns = 1;</code>
         */
        public int getColumnsCount() {
          return columns_.size();
        }
        /**
         * <code>repeated .ColumnMetaData columns = 1;</code>
         */
        public org.apache.calcite.avatica.proto.Common.ColumnMetaData getColumns(int index) {
          return columns_.get(index);
        }
        /**
         * <code>repeated .ColumnMetaData columns = 1;</code>
         */
        public org.apache.calcite.avatica.proto.Common.ColumnMetaDataOrBuilder getColumnsOrBuilder(
            int index) {
          return columns_.get(index);
        }

        public static final int SQL_FIELD_NUMBER = 2;
        private volatile java.lang.Object sql_;
        /**
         * <code>string sql = 2;</code>
         */
        public java.lang.String getSql() {
          java.lang.Object ref = sql_;
          if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
          } else {
            com.google.protobuf.ByteString bs = 
                (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            sql_ = s;
            return s;
          }
        }
        /**
         * <code>string sql = 2;</code>
         */
        public com.google.protobuf.ByteString
            getSqlBytes() {
          java.lang.Object ref = sql_;
          if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b = 
                com.google.protobuf.ByteString.copyFromUtf8(
                    (java.lang.String) ref);
            sql_ = b;
            return b;
          } else {
            return (com.google.protobuf.ByteString) ref;
          }
        }

        public static final int PARAMETERS_FIELD_NUMBER = 3;
        private java.util.List<org.apache.calcite.avatica.proto.Common.AvaticaParameter> parameters_;
        /**
         * <code>repeated .AvaticaParameter parameters = 3;</code>
         */
        public java.util.List<org.apache.calcite.avatica.proto.Common.AvaticaParameter> getParametersList() {
          return parameters_;
        }
        /**
         * <code>repeated .AvaticaParameter parameters = 3;</code>
         */
        public java.util.List<? extends org.apache.calcite.avatica.proto.Common.AvaticaParameterOrBuilder> 
            getParametersOrBuilderList() {
          return parameters_;
        }
        /**
         * <code>repeated .AvaticaParameter parameters = 3;</code>
         */
        public int getParametersCount() {
          return parameters_.size();
        }
        /**
         * <code>repeated .AvaticaParameter parameters = 3;</code>
         */
        public org.apache.calcite.avatica.proto.Common.AvaticaParameter getParameters(int index) {
          return parameters_.get(index);
        }
        /**
         * <code>repeated .AvaticaParameter parameters = 3;</code>
         */
        public org.apache.calcite.avatica.proto.Common.AvaticaParameterOrBuilder getParametersOrBuilder(
            int index) {
          return parameters_.get(index);
        }

        public static final int CURSOR_FACTORY_FIELD_NUMBER = 4;
        private org.apache.calcite.avatica.proto.Common.CursorFactory cursorFactory_;
        /**
         * <code>.CursorFactory cursor_factory = 4;</code>
         */
        public boolean hasCursorFactory() {
          return cursorFactory_ != null;
        }
        /**
         * <code>.CursorFactory cursor_factory = 4;</code>
         */
        public org.apache.calcite.avatica.proto.Common.CursorFactory getCursorFactory() {
          return cursorFactory_ == null ? org.apache.calcite.avatica.proto.Common.CursorFactory.getDefaultInstance() : cursorFactory_;
        }
        /**
         * <code>.CursorFactory cursor_factory = 4;</code>
         */
        public org.apache.calcite.avatica.proto.Common.CursorFactoryOrBuilder getCursorFactoryOrBuilder() {
          return getCursorFactory();
        }

        public static final int STATEMENTTYPE_FIELD_NUMBER = 5;
        private int statementType_;
        /**
         * <code>.StatementType statementType = 5;</code>
         */
        public int getStatementTypeValue() {
          return statementType_;
        }
        /**
         * <code>.StatementType statementType = 5;</code>
         */
        public org.apache.calcite.avatica.proto.Common.StatementType getStatementType() {
          @SuppressWarnings("deprecation")
          org.apache.calcite.avatica.proto.Common.StatementType result = org.apache.calcite.avatica.proto.Common.StatementType.valueOf(statementType_);
          return result == null ? org.apache.calcite.avatica.proto.Common.StatementType.UNRECOGNIZED : result;
        }

        private byte memoizedIsInitialized = -1;
        @java.lang.Override
        public final boolean isInitialized() {
          byte isInitialized = memoizedIsInitialized;
          if (isInitialized == 1) return true;
          if (isInitialized == 0) return false;

          memoizedIsInitialized = 1;
          return true;
        }

        @java.lang.Override
        public void writeTo(com.google.protobuf.CodedOutputStream output)
                            throws java.io.IOException {
          for (int i = 0; i < columns_.size(); i++) {
            output.writeMessage(1, columns_.get(i));
          }
          if (!getSqlBytes().isEmpty()) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 2, sql_);
          }
          for (int i = 0; i < parameters_.size(); i++) {
            output.writeMessage(3, parameters_.get(i));
          }
          if (cursorFactory_ != null) {
            output.writeMessage(4, getCursorFactory());
          }
          if (statementType_ != org.apache.calcite.avatica.proto.Common.StatementType.SELECT.getNumber()) {
            output.writeEnum(5, statementType_);
          }
          unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
          int size = memoizedSize;
          if (size != -1) return size;

          size = 0;
          for (int i = 0; i < columns_.size(); i++) {
            size += com.google.protobuf.CodedOutputStream
              .computeMessageSize(1, columns_.get(i));
          }
          if (!getSqlBytes().isEmpty()) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, sql_);
          }
          for (int i = 0; i < parameters_.size(); i++) {
            size += com.google.protobuf.CodedOutputStream
              .computeMessageSize(3, parameters_.get(i));
          }
          if (cursorFactory_ != null) {
            size += com.google.protobuf.CodedOutputStream
              .computeMessageSize(4, getCursorFactory());
          }
          if (statementType_ != org.apache.calcite.avatica.proto.Common.StatementType.SELECT.getNumber()) {
            size += com.google.protobuf.CodedOutputStream
              .computeEnumSize(5, statementType_);
          }
          size += unknownFields.getSerializedSize();
          memoizedSize = size;
          return size;
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
          if (obj == this) {
           return true;
          }
          if (!(obj instanceof org.apache.calcite.avatica.proto.Common.Signature)) {
            return super.equals(obj);
          }
          org.apache.calcite.avatica.proto.Common.Signature other = (org.apache.calcite.avatica.proto.Common.Signature) obj;

          boolean result = true;
          result = result && getColumnsList()
              .equals(other.getColumnsList());
          result = result && getSql()
              .equals(other.getSql());
          result = result && getParametersList()
              .equals(other.getParametersList());
          result = result && (hasCursorFactory() == other.hasCursorFactory());
          if (hasCursorFactory()) {
            result = result && getCursorFactory()
                .equals(other.getCursorFactory());
          }
          result = result && statementType_ == other.statementType_;
          result = result && unknownFields.equals(other.unknownFields);
          return result;
        }

        @java.lang.Override
        public int hashCode() {
          if (memoizedHashCode != 0) {
            return memoizedHashCode;
          }
          int hash = 41;
          hash = (19 * hash) + getDescriptor().hashCode();
          if (getColumnsCount() > 0) {
            hash = (37 * hash) + COLUMNS_FIELD_NUMBER;
            hash = (53 * hash) + getColumnsList().hashCode();
          }
          hash = (37 * hash) + SQL_FIELD_NUMBER;
          hash = (53 * hash) + getSql().hashCode();
          if (getParametersCount() > 0) {
            hash = (37 * hash) + PARAMETERS_FIELD_NUMBER;
            hash = (53 * hash) + getParametersList().hashCode();
          }
          if (hasCursorFactory()) {
            hash = (37 * hash) + CURSOR_FACTORY_FIELD_NUMBER;
            hash = (53 * hash) + getCursorFactory().hashCode();
          }
          hash = (37 * hash) + STATEMENTTYPE_FIELD_NUMBER;
          hash = (53 * hash) + statementType_;
          hash = (29 * hash) + unknownFields.hashCode();
          memoizedHashCode = hash;
          return hash;
        }

        public static org.apache.calcite.avatica.proto.Common.Signature parseFrom(
            java.nio.ByteBuffer data)
            throws com.google.protobuf.InvalidProtocolBufferException {
          return PARSER.parseFrom(data);
        }
        public static org.apache.calcite.avatica.proto.Common.Signature parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
          return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.apache.calcite.avatica.proto.Common.Signature parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
          return PARSER.parseFrom(data);
        }
        public static org.apache.calcite.avatica.proto.Common.Signature parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
          return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.apache.calcite.avatica.proto.Common.Signature parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
          return PARSER.parseFrom(data);
        }
        public static org.apache.calcite.avatica.proto.Common.Signature parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
          return PARSER.parseFrom(data, extensionRegistry);
        }
        public static org.apache.calcite.avatica.proto.Common.Signature parseFrom(java.io.InputStream input)
            throws java.io.IOException {
          return com.google.protobuf.GeneratedMessageV3
              .parseWithIOException(PARSER, input);
        }
        public static org.apache.calcite.avatica.proto.Common.Signature parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
          return com.google.protobuf.GeneratedMessageV3
              .parseWithIOException(PARSER, input, extensionRegistry);
        }
        public static org.apache.calcite.avatica.proto.Common.Signature parseDelimitedFrom(java.io.InputStream input)
            throws java.io.IOException {
          return com.google.protobuf.GeneratedMessageV3
              .parseDelimitedWithIOException(PARSER, input);
        }
        public static org.apache.calcite.avatica.proto.Common.Signature parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
          return com.google.protobuf.GeneratedMessageV3
              .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }
        public static org.apache.calcite.avatica.proto.Common.Signature parseFrom(
            com.google.protobuf.CodedInputStream input)
            throws java.io.IOException {
          return com.google.protobuf.GeneratedMessageV3
              .parseWithIOException(PARSER, input);
        }
        public static org.apache.calcite.avatica.proto.Common.Signature parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
          return com.google.protobuf.GeneratedMessageV3
              .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() { return newBuilder(); }
        public static Builder newBuilder() {
          return DEFAULT_INSTANCE.toBuilder();
        }
        public static Builder newBuilder(org.apache.calcite.avatica.proto.Common.Signature prototype) {
          return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }
        @java.lang.Override
        public Builder toBuilder() {
          return this == DEFAULT_INSTANCE
              ? new Builder() : new Builder().mergeFrom(this);
        }

        @java.lang.Override
        protected Builder newBuilderForType(
            com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
          Builder builder = new Builder(parent);
          return builder;
        }
        /**
         * <pre>
         * Results of preparing a statement
         * </pre>
         *
         * Protobuf type {@code Signature}
         */
        public static final class Builder extends
            com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:Signature)
            org.apache.calcite.avatica.proto.Common.SignatureOrBuilder {
          public static final com.google.protobuf.Descriptors.Descriptor
              getDescriptor() {
            return org.apache.calcite.avatica.proto.Common.internal_static_Signature_descriptor;
          }

          @java.lang.Override
          protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
              internalGetFieldAccessorTable() {
            return org.apache.calcite.avatica.proto.Common.internal_static_Signature_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                    org.apache.calcite.avatica.proto.Common.Signature.class, org.apache.calcite.avatica.proto.Common.Signature.Builder.class);
          }

          // Construct using org.apache.calcite.avatica.proto.Common.Signature.newBuilder()
          private Builder() {
            maybeForceBuilderInitialization();
          }

          private Builder(
              com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
          }
          private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessageV3
                    .alwaysUseFieldBuilders) {
              getColumnsFieldBuilder();
              getParametersFieldBuilder();
            }
          }
          @java.lang.Override
          public Builder clear() {
            super.clear();
            if (columnsBuilder_ == null) {
              columns_ = java.util.Collections.emptyList();
              bitField0_ = (bitField0_ & ~0x00000001);
            } else {
              columnsBuilder_.clear();
            }
            sql_ = "";

            if (parametersBuilder_ == null) {
              parameters_ = java.util.Collections.emptyList();
              bitField0_ = (bitField0_ & ~0x00000004);
            } else {
              parametersBuilder_.clear();
            }
            if (cursorFactoryBuilder_ == null) {
              cursorFactory_ = null;
            } else {
              cursorFactory_ = null;
              cursorFactoryBuilder_ = null;
            }
            statementType_ = 0;

            return this;
          }

          @java.lang.Override
          public com.google.protobuf.Descriptors.Descriptor
              getDescriptorForType() {
            return org.apache.calcite.avatica.proto.Common.internal_static_Signature_descriptor;
          }

          @java.lang.Override
          public org.apache.calcite.avatica.proto.Common.Signature getDefaultInstanceForType() {
            return org.apache.calcite.avatica.proto.Common.Signature.getDefaultInstance();
          }

          @java.lang.Override
          public org.apache.calcite.avatica.proto.Common.Signature build() {
            org.apache.calcite.avatica.proto.Common.Signature result = buildPartial();
            if (!result.isInitialized()) {
              throw newUninitializedMessageException(result);
            }
            return result;
          }

          @java.lang.Override
          public org.apache.calcite.avatica.proto.Common.Signature buildPartial() {
            org.apache.calcite.avatica.proto.Common.Signature result = new org.apache.calcite.avatica.proto.Common.Signature(this);
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (columnsBuilder_ == null) {
              if (((bitField0_ & 0x00000001) == 0x00000001)) {
                columns_ = java.util.Collections.unmodifiableList(columns_);
                bitField0_ = (bitField0_ & ~0x00000001);
              }
              result.columns_ = columns_;
            } else {
              result.columns_ = columnsBuilder_.build();
            }
            result.sql_ = sql_;
            if (parametersBuilder_ == null) {
              if (((bitField0_ & 0x00000004) == 0x00000004)) {
                parameters_ = java.util.Collections.unmodifiableList(parameters_);
                bitField0_ = (bitField0_ & ~0x00000004);
              }
              result.parameters_ = parameters_;
            } else {
              result.parameters_ = parametersBuilder_.build();
            }
            if (cursorFactoryBuilder_ == null) {
              result.cursorFactory_ = cursorFactory_;
            } else {
              result.cursorFactory_ = cursorFactoryBuilder_.build();
            }
            result.statementType_ = statementType_;
            result.bitField0_ = to_bitField0_;
            onBuilt();
            return result;
          }

          @java.lang.Override
          public Builder clone() {
            return (Builder) super.clone();
          }
          @java.lang.Override
          public Builder setField(
              com.google.protobuf.Descriptors.FieldDescriptor field,
              java.lang.Object value) {
            return (Builder) super.setField(field, value);
          }
          @java.lang.Override
          public Builder clearField(
              com.google.protobuf.Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
          }
          @java.lang.Override
          public Builder clearOneof(
              com.google.protobuf.Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
          }
          @java.lang.Override
          public Builder setRepeatedField(
              com.google.protobuf.Descriptors.FieldDescriptor field,
              int index, java.lang.Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
          }
          @java.lang.Override
          public Builder addRepeatedField(
              com.google.protobuf.Descriptors.FieldDescriptor field,
              java.lang.Object value) {
            return (Builder) super.addRepeatedField(field, value);
          }
          @java.lang.Override
          public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof org.apache.calcite.avatica.proto.Common.Signature) {
              return mergeFrom((org.apache.calcite.avatica.proto.Common.Signature)other);
            } else {
              super.mergeFrom(other);
              return this;
            }
          }

          public Builder mergeFrom(org.apache.calcite.avatica.proto.Common.Signature other) {
            if (other == org.apache.calcite.avatica.proto.Common.Signature.getDefaultInstance()) return this;
            if (columnsBuilder_ == null) {
              if (!other.columns_.isEmpty()) {
                if (columns_.isEmpty()) {
                  columns_ = other.columns_;
                  bitField0_ = (bitField0_ & ~0x00000001);
                } else {
                  ensureColumnsIsMutable();
                  columns_.addAll(other.columns_);
                }
                onChanged();
              }
            } else {
              if (!other.columns_.isEmpty()) {
                if (columnsBuilder_.isEmpty()) {
                  columnsBuilder_.dispose();
                  columnsBuilder_ = null;
                  columns_ = other.columns_;
                  bitField0_ = (bitField0_ & ~0x00000001);
                  columnsBuilder_ = 
                    com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                       getColumnsFieldBuilder() : null;
                } else {
                  columnsBuilder_.addAllMessages(other.columns_);
                }
              }
            }
            if (!other.getSql().isEmpty()) {
              sql_ = other.sql_;
              onChanged();
            }
            if (parametersBuilder_ == null) {
              if (!other.parameters_.isEmpty()) {
                if (parameters_.isEmpty()) {
                  parameters_ = other.parameters_;
                  bitField0_ = (bitField0_ & ~0x00000004);
                } else {
                  ensureParametersIsMutable();
                  parameters_.addAll(other.parameters_);
                }
                onChanged();
              }
            } else {
              if (!other.parameters_.isEmpty()) {
                if (parametersBuilder_.isEmpty()) {
                  parametersBuilder_.dispose();
                  parametersBuilder_ = null;
                  parameters_ = other.parameters_;
                  bitField0_ = (bitField0_ & ~0x00000004);
                  parametersBuilder_ = 
                    com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                       getParametersFieldBuilder() : null;
                } else {
                  parametersBuilder_.addAllMessages(other.parameters_);
                }
              }
            }
            if (other.hasCursorFactory()) {
              mergeCursorFactory(other.getCursorFactory());
            }
            if (other.statementType_ != 0) {
              setStatementTypeValue(other.getStatementTypeValue());
            }
            this.mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
          }

          @java.lang.Override
          public final boolean isInitialized() {
            return true;
          }

          @java.lang.Override
          public Builder mergeFrom(
              com.google.protobuf.CodedInputStream input,
              com.google.protobuf.ExtensionRegistryLite extensionRegistry)
              throws java.io.IOException {
            org.apache.calcite.avatica.proto.Common.Signature parsedMessage = null;
            try {
              parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
              parsedMessage = (org.apache.calcite.avatica.proto.Common.Signature) e.getUnfinishedMessage();
              throw e.unwrapIOException();
            } finally {
              if (parsedMessage != null) {
                mergeFrom(parsedMessage);
              }
            }
            return this;
          }
          private int bitField0_;

          private java.util.List<org.apache.calcite.avatica.proto.Common.ColumnMetaData> columns_ =
            java.util.Collections.emptyList();
          private void ensureColumnsIsMutable() {
            if (!((bitField0_ & 0x00000001) == 0x00000001)) {
              columns_ = new java.util.ArrayList<org.apache.calcite.avatica.proto.Common.ColumnMetaData>(columns_);
              bitField0_ |= 0x00000001;
             }
          }

          private com.google.protobuf.RepeatedFieldBuilderV3<
              org.apache.calcite.avatica.proto.Common.ColumnMetaData, org.apache.calcite.avatica.proto.Common.ColumnMetaData.Builder, org.apache.calcite.avatica.proto.Common.ColumnMetaDataOrBuilder> columnsBuilder_;

          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public java.util.List<org.apache.calcite.avatica.proto.Common.ColumnMetaData> getColumnsList() {
            if (columnsBuilder_ == null) {
              return java.util.Collections.unmodifiableList(columns_);
            } else {
              return columnsBuilder_.getMessageList();
            }
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public int getColumnsCount() {
            if (columnsBuilder_ == null) {
              return columns_.size();
            } else {
              return columnsBuilder_.getCount();
            }
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public org.apache.calcite.avatica.proto.Common.ColumnMetaData getColumns(int index) {
            if (columnsBuilder_ == null) {
              return columns_.get(index);
            } else {
              return columnsBuilder_.getMessage(index);
            }
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public Builder setColumns(
              int index, org.apache.calcite.avatica.proto.Common.ColumnMetaData value) {
            if (columnsBuilder_ == null) {
              if (value == null) {
                throw new NullPointerException();
              }
              ensureColumnsIsMutable();
              columns_.set(index, value);
              onChanged();
            } else {
              columnsBuilder_.setMessage(index, value);
            }
            return this;
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public Builder setColumns(
              int index, org.apache.calcite.avatica.proto.Common.ColumnMetaData.Builder builderForValue) {
            if (columnsBuilder_ == null) {
              ensureColumnsIsMutable();
              columns_.set(index, builderForValue.build());
              onChanged();
            } else {
              columnsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public Builder addColumns(org.apache.calcite.avatica.proto.Common.ColumnMetaData value) {
            if (columnsBuilder_ == null) {
              if (value == null) {
                throw new NullPointerException();
              }
              ensureColumnsIsMutable();
              columns_.add(value);
              onChanged();
            } else {
              columnsBuilder_.addMessage(value);
            }
            return this;
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public Builder addColumns(
              int index, org.apache.calcite.avatica.proto.Common.ColumnMetaData value) {
            if (columnsBuilder_ == null) {
              if (value == null) {
                throw new NullPointerException();
              }
              ensureColumnsIsMutable();
              columns_.add(index, value);
              onChanged();
            } else {
              columnsBuilder_.addMessage(index, value);
            }
            return this;
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public Builder addColumns(
              org.apache.calcite.avatica.proto.Common.ColumnMetaData.Builder builderForValue) {
            if (columnsBuilder_ == null) {
              ensureColumnsIsMutable();
              columns_.add(builderForValue.build());
              onChanged();
            } else {
              columnsBuilder_.addMessage(builderForValue.build());
            }
            return this;
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public Builder addColumns(
              int index, org.apache.calcite.avatica.proto.Common.ColumnMetaData.Builder builderForValue) {
            if (columnsBuilder_ == null) {
              ensureColumnsIsMutable();
              columns_.add(index, builderForValue.build());
              onChanged();
            } else {
              columnsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public Builder addAllColumns(
              java.lang.Iterable<? extends org.apache.calcite.avatica.proto.Common.ColumnMetaData> values) {
            if (columnsBuilder_ == null) {
              ensureColumnsIsMutable();
              com.google.protobuf.AbstractMessageLite.Builder.addAll(
                  values, columns_);
              onChanged();
            } else {
              columnsBuilder_.addAllMessages(values);
            }
            return this;
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public Builder clearColumns() {
            if (columnsBuilder_ == null) {
              columns_ = java.util.Collections.emptyList();
              bitField0_ = (bitField0_ & ~0x00000001);
              onChanged();
            } else {
              columnsBuilder_.clear();
            }
            return this;
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public Builder removeColumns(int index) {
            if (columnsBuilder_ == null) {
              ensureColumnsIsMutable();
              columns_.remove(index);
              onChanged();
            } else {
              columnsBuilder_.remove(index);
            }
            return this;
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public org.apache.calcite.avatica.proto.Common.ColumnMetaData.Builder getColumnsBuilder(
              int index) {
            return getColumnsFieldBuilder().getBuilder(index);
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public org.apache.calcite.avatica.proto.Common.ColumnMetaDataOrBuilder getColumnsOrBuilder(
              int index) {
            if (columnsBuilder_ == null) {
              return columns_.get(index);  } else {
              return columnsBuilder_.getMessageOrBuilder(index);
            }
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public java.util.List<? extends org.apache.calcite.avatica.proto.Common.ColumnMetaDataOrBuilder> 
               getColumnsOrBuilderList() {
            if (columnsBuilder_ != null) {
              return columnsBuilder_.getMessageOrBuilderList();
            } else {
              return java.util.Collections.unmodifiableList(columns_);
            }
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public org.apache.calcite.avatica.proto.Common.ColumnMetaData.Builder addColumnsBuilder() {
            return getColumnsFieldBuilder().addBuilder(
                org.apache.calcite.avatica.proto.Common.ColumnMetaData.getDefaultInstance());
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public org.apache.calcite.avatica.proto.Common.ColumnMetaData.Builder addColumnsBuilder(
              int index) {
            return getColumnsFieldBuilder().addBuilder(
                index, org.apache.calcite.avatica.proto.Common.ColumnMetaData.getDefaultInstance());
          }
          /**
           * <code>repeated .ColumnMetaData columns = 1;</code>
           */
          public java.util.List<org.apache.calcite.avatica.proto.Common.ColumnMetaData.Builder> 
               getColumnsBuilderList() {
            return getColumnsFieldBuilder().getBuilderList();
          }
          private com.google.protobuf.RepeatedFieldBuilderV3<
              org.apache.calcite.avatica.proto.Common.ColumnMetaData, org.apache.calcite.avatica.proto.Common.ColumnMetaData.Builder, org.apache.calcite.avatica.proto.Common.ColumnMetaDataOrBuilder> 
              getColumnsFieldBuilder() {
            if (columnsBuilder_ == null) {
              columnsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
                  org.apache.calcite.avatica.proto.Common.ColumnMetaData, org.apache.calcite.avatica.proto.Common.ColumnMetaData.Builder, org.apache.calcite.avatica.proto.Common.ColumnMetaDataOrBuilder>(
                      columns_,
                      ((bitField0_ & 0x00000001) == 0x00000001),
                      getParentForChildren(),
                      isClean());
              columns_ = null;
            }
            return columnsBuilder_;
          }

          private java.lang.Object sql_ = "";
          /**
           * <code>string sql = 2;</code>
           */
          public java.lang.String getSql() {
            java.lang.Object ref = sql_;
            if (!(ref instanceof java.lang.String)) {
              com.google.protobuf.ByteString bs =
                  (com.google.protobuf.ByteString) ref;
              java.lang.String s = bs.toStringUtf8();
              sql_ = s;
              return s;
            } else {
              return (java.lang.String) ref;
            }
          }
          /**
           * <code>string sql = 2;</code>
           */
          public com.google.protobuf.ByteString
              getSqlBytes() {
            java.lang.Object ref = sql_;
            if (ref instanceof String) {
              com.google.protobuf.ByteString b = 
                  com.google.protobuf.ByteString.copyFromUtf8(
                      (java.lang.String) ref);
              sql_ = b;
              return b;
            } else {
              return (com.google.protobuf.ByteString) ref;
            }
          }
          /**
           * <code>string sql = 2;</code>
           */
          public Builder setSql(
              java.lang.String value) {
            if (value == null) {
        throw new NullPointerException();
      }
  
            sql_ = value;
            onChanged();
            return this;
          }
          /**
           * <code>string sql = 2;</code>
           */
          public Builder clearSql() {
        
            sql_ = getDefaultInstance().getSql();
            onChanged();
            return this;
          }
          /**
           * <code>string sql = 2;</code>
           */
          public Builder setSqlBytes(
              com.google.protobuf.ByteString value) {
            if (value == null) {
        throw new NullPointerException();
      }
      checkByteStringIsUtf8(value);
        
            sql_ = value;
            onChanged();
            return this;
          }

          private java.util.List<org.apache.calcite.avatica.proto.Common.AvaticaParameter> parameters_ =
            java.util.Collections.emptyList();
          private void ensureParametersIsMutable() {
            if (!((bitField0_ & 0x00000004) == 0x00000004)) {
              parameters_ = new java.util.ArrayList<org.apache.calcite.avatica.proto.Common.AvaticaParameter>(parameters_);
              bitField0_ |= 0x00000004;
             }
          }

          private com.google.protobuf.RepeatedFieldBuilderV3<
              org.apache.calcite.avatica.proto.Common.AvaticaParameter, org.apache.calcite.avatica.proto.Common.AvaticaParameter.Builder, org.apache.calcite.avatica.proto.Common.AvaticaParameterOrBuilder> parametersBuilder_;

          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public java.util.List<org.apache.calcite.avatica.proto.Common.AvaticaParameter> getParametersList() {
            if (parametersBuilder_ == null) {
              return java.util.Collections.unmodifiableList(parameters_);
            } else {
              return parametersBuilder_.getMessageList();
            }
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public int getParametersCount() {
            if (parametersBuilder_ == null) {
              return parameters_.size();
            } else {
              return parametersBuilder_.getCount();
            }
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public org.apache.calcite.avatica.proto.Common.AvaticaParameter getParameters(int index) {
            if (parametersBuilder_ == null) {
              return parameters_.get(index);
            } else {
              return parametersBuilder_.getMessage(index);
            }
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public Builder setParameters(
              int index, org.apache.calcite.avatica.proto.Common.AvaticaParameter value) {
            if (parametersBuilder_ == null) {
              if (value == null) {
                throw new NullPointerException();
              }
              ensureParametersIsMutable();
              parameters_.set(index, value);
              onChanged();
            } else {
              parametersBuilder_.setMessage(index, value);
            }
            return this;
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public Builder setParameters(
              int index, org.apache.calcite.avatica.proto.Common.AvaticaParameter.Builder builderForValue) {
            if (parametersBuilder_ == null) {
              ensureParametersIsMutable();
              parameters_.set(index, builderForValue.build());
              onChanged();
            } else {
              parametersBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public Builder addParameters(org.apache.calcite.avatica.proto.Common.AvaticaParameter value) {
            if (parametersBuilder_ == null) {
              if (value == null) {
                throw new NullPointerException();
              }
              ensureParametersIsMutable();
              parameters_.add(value);
              onChanged();
            } else {
              parametersBuilder_.addMessage(value);
            }
            return this;
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public Builder addParameters(
              int index, org.apache.calcite.avatica.proto.Common.AvaticaParameter value) {
            if (parametersBuilder_ == null) {
              if (value == null) {
                throw new NullPointerException();
              }
              ensureParametersIsMutable();
              parameters_.add(index, value);
              onChanged();
            } else {
              parametersBuilder_.addMessage(index, value);
            }
            return this;
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public Builder addParameters(
              org.apache.calcite.avatica.proto.Common.AvaticaParameter.Builder builderForValue) {
            if (parametersBuilder_ == null) {
              ensureParametersIsMutable();
              parameters_.add(builderForValue.build());
              onChanged();
            } else {
              parametersBuilder_.addMessage(builderForValue.build());
            }
            return this;
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public Builder addParameters(
              int index, org.apache.calcite.avatica.proto.Common.AvaticaParameter.Builder builderForValue) {
            if (parametersBuilder_ == null) {
              ensureParametersIsMutable();
              parameters_.add(index, builderForValue.build());
              onChanged();
            } else {
              parametersBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public Builder addAllParameters(
              java.lang.Iterable<? extends org.apache.calcite.avatica.proto.Common.AvaticaParameter> values) {
            if (parametersBuilder_ == null) {
              ensureParametersIsMutable();
              com.google.protobuf.AbstractMessageLite.Builder.addAll(
                  values, parameters_);
              onChanged();
            } else {
              parametersBuilder_.addAllMessages(values);
            }
            return this;
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public Builder clearParameters() {
            if (parametersBuilder_ == null) {
              parameters_ = java.util.Collections.emptyList();
              bitField0_ = (bitField0_ & ~0x00000004);
              onChanged();
            } else {
              parametersBuilder_.clear();
            }
            return this;
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public Builder removeParameters(int index) {
            if (parametersBuilder_ == null) {
              ensureParametersIsMutable();
              parameters_.remove(index);
              onChanged();
            } else {
              parametersBuilder_.remove(index);
            }
            return this;
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public org.apache.calcite.avatica.proto.Common.AvaticaParameter.Builder getParametersBuilder(
              int index) {
            return getParametersFieldBuilder().getBuilder(index);
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public org.apache.calcite.avatica.proto.Common.AvaticaParameterOrBuilder getParametersOrBuilder(
              int index) {
            if (parametersBuilder_ == null) {
              return parameters_.get(index);  } else {
              return parametersBuilder_.getMessageOrBuilder(index);
            }
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public java.util.List<? extends org.apache.calcite.avatica.proto.Common.AvaticaParameterOrBuilder> 
               getParametersOrBuilderList() {
            if (parametersBuilder_ != null) {
              return parametersBuilder_.getMessageOrBuilderList();
            } else {
              return java.util.Collections.unmodifiableList(parameters_);
            }
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public org.apache.calcite.avatica.proto.Common.AvaticaParameter.Builder addParametersBuilder() {
            return getParametersFieldBuilder().addBuilder(
                org.apache.calcite.avatica.proto.Common.AvaticaParameter.getDefaultInstance());
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public org.apache.calcite.avatica.proto.Common.AvaticaParameter.Builder addParametersBuilder(
              int index) {
            return getParametersFieldBuilder().addBuilder(
                index, org.apache.calcite.avatica.proto.Common.AvaticaParameter.getDefaultInstance());
          }
          /**
           * <code>repeated .AvaticaParameter parameters = 3;</code>
           */
          public java.util.List<org.apache.calcite.avatica.proto.Common.AvaticaParameter.Builder> 
               getParametersBuilderList() {
            return getParametersFieldBuilder().getBuilderList();
          }
          private com.google.protobuf.RepeatedFieldBuilderV3<
              org.apache.calcite.avatica.proto.Common.AvaticaParameter, org.apache.calcite.avatica.proto.Common.AvaticaParameter.Builder, org.apache.calcite.avatica.proto.Common.AvaticaParameterOrBuilder> 
              getParametersFieldBuilder() {
            if (parametersBuilder_ == null) {
              parametersBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
                  org.apache.calcite.avatica.proto.Common.AvaticaParameter, org.apache.calcite.avatica.proto.Common.AvaticaParameter.Builder, org.apache.calcite.avatica.proto.Common.AvaticaParameterOrBuilder>(
                      parameters_,
                      ((bitField0_ & 0x00000004) == 0x00000004),
                      getParentForChildren(),
                      isClean());
              parameters_ = null;
            }
            return parametersBuilder_;
          }

          private org.apache.calcite.avatica.proto.Common.CursorFactory cursorFactory_ = null;
          private com.google.protobuf.SingleFieldBuilderV3<
              org.apache.calcite.avatica.proto.Common.CursorFactory, org.apache.calcite.avatica.proto.Common.CursorFactory.Builder, org.apache.calcite.avatica.proto.Common.CursorFactoryOrBuilder> cursorFactoryBuilder_;
          /**
           * <code>.CursorFactory cursor_factory = 4;</code>
           */
          public boolean hasCursorFactory() {
            return cursorFactoryBuilder_ != null || cursorFactory_ != null;
          }
          /**
           * <code>.CursorFactory cursor_factory = 4;</code>
           */
          public org.apache.calcite.avatica.proto.Common.CursorFactory getCursorFactory() {
            if (cursorFactoryBuilder_ == null) {
              return cursorFactory_ == null ? org.apache.calcite.avatica.proto.Common.CursorFactory.getDefaultInstance() : cursorFactory_;
            } else {
              return cursorFactoryBuilder_.getMessage();
            }
          }
          /**
           * <code>.CursorFactory cursor_factory = 4;</code>
           */
          public Builder setCursorFactory(org.apache.calcite.avatica.proto.Common.CursorFactory value) {
            if (cursorFactoryBuilder_ == null) {
              if (value == null) {
                throw new NullPointerException();
              }
              cursorFactory_ = value;
              onChanged();
            } else {
              cursorFactoryBuilder_.setMessage(value);
            }

            return this;
          }
          /**
           * <code>.CursorFactory cursor_factory = 4;</code>
           */
          public Builder setCursorFactory(
              org.apache.calcite.avatica.proto.Common.CursorFactory.Builder builderForValue) {
            if (cursorFactoryBuilder_ == null) {
              cursorFactory_ = builderForValue.build();
              onChanged();
            } else {
              cursorFactoryBuilder_.setMessage(builderForValue.build());
            }

            return this;
          }
          /**
           * <code>.CursorFactory cursor_factory = 4;</code>
           */
          public Builder mergeCursorFactory(org.apache.calcite.avatica.proto.Common.CursorFactory value) {
            if (cursorFactoryBuilder_ == null) {
              if (cursorFactory_ != null) {
                cursorFactory_ =
                  org.apache.calcite.avatica.proto.Common.CursorFactory.newBuilder(cursorFactory_).mergeFrom(value).buildPartial();
              } else {
                cursorFactory_ = value;
              }
              onChanged();
            } else {
              cursorFactoryBuilder_.mergeFrom(value);
            }

            return this;
          }
          /**
           * <code>.CursorFactory cursor_factory = 4;</code>
           */
          public Builder clearCursorFactory() {
            if (cursorFactoryBuilder_ == null) {
              cursorFactory_ = null;
              onChanged();
            } else {
              cursorFactory_ = null;
              cursorFactoryBuilder_ = null;
            }

            return this;
          }
          /**
           * <code>.CursorFactory cursor_factory = 4;</code>
           */
          public org.apache.calcite.avatica.proto.Common.CursorFactory.Builder getCursorFactoryBuilder() {
        
            onChanged();
            return getCursorFactoryFieldBuilder().getBuilder();
          }
          /**
           * <code>.CursorFactory cursor_factory = 4;</code>
           */
          public org.apache.calcite.avatica.proto.Common.CursorFactoryOrBuilder getCursorFactoryOrBuilder() {
            if (cursorFactoryBuilder_ != null) {
              return cursorFactoryBuilder_.getMessageOrBuilder();
            } else {
              return cursorFactory_ == null ?
                  org.apache.calcite.avatica.proto.Common.CursorFactory.getDefaultInstance() : cursorFactory_;
            }
          }
          /**
           * <code>.CursorFactory cursor_factory = 4;</code>
           */
          private com.google.protobuf.SingleFieldBuilderV3<
              org.apache.calcite.avatica.proto.Common.CursorFactory, org.apache.calcite.avatica.proto.Common.CursorFactory.Builder, org.apache.calcite.avatica.proto.Common.CursorFactoryOrBuilder> 
              getCursorFactoryFieldBuilder() {
            if (cursorFactoryBuilder_ == null) {
              cursorFactoryBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
                  org.apache.calcite.avatica.proto.Common.CursorFactory, org.apache.calcite.avatica.proto.Common.CursorFactory.Builder, org.apache.calcite.avatica.proto.Common.CursorFactoryOrBuilder>(
                      getCursorFactory(),
                      getParentForChildren(),
                      isClean());
              cursorFactory_ = null;
            }
            return cursorFactoryBuilder_;
          }

          private int statementType_ = 0;
          /**
           * <code>.StatementType statementType = 5;</code>
           */
          public int getStatementTypeValue() {
            return statementType_;
          }
          /**
           * <code>.StatementType statementType = 5;</code>
           */
          public Builder setStatementTypeValue(int value) {
            statementType_ = value;
            onChanged();
            return this;
          }
          /**
           * <code>.StatementType statementType = 5;</code>
           */
          public org.apache.calcite.avatica.proto.Common.StatementType getStatementType() {
            @SuppressWarnings("deprecation")
            org.apache.calcite.avatica.proto.Common.StatementType result = org.apache.calcite.avatica.proto.Common.StatementType.valueOf(statementType_);
            return result == null ? org.apache.calcite.avatica.proto.Common.StatementType.UNRECOGNIZED : result;
          }
          /**
           * <code>.StatementType statementType = 5;</code>
           */
          public Builder setStatementType(org.apache.calcite.avatica.proto.Common.StatementType value) {
            if (value == null) {
              throw new NullPointerException();
            }
        
            statementType_ = value.getNumber();
            onChanged();
            return this;
          }
          /**
           * <code>.StatementType statementType = 5;</code>
           */
          public Builder clearStatementType() {
        
            statementType_ = 0;
            onChanged();
            return this;
          }
          @java.lang.Override
          public final Builder setUnknownFields(
              final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.setUnknownFieldsProto3(unknownFields);
          }

          @java.lang.Override
          public final Builder mergeUnknownFields(
              final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
          }


          // @@protoc_insertion_point(builder_scope:Signature)
        }

        // @@protoc_insertion_point(class_scope:Signature)
        private static final org.apache.calcite.avatica.proto.Common.Signature DEFAULT_INSTANCE;
        static {
          DEFAULT_INSTANCE = new org.apache.calcite.avatica.proto.Common.Signature();
        }

        public static org.apache.calcite.avatica.proto.Common.Signature getDefaultInstance() {
          return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<Signature>
            PARSER = new com.google.protobuf.AbstractParser<Signature>() {
          @java.lang.Override
          public Signature parsePartialFrom(
              com.google.protobuf.CodedInputStream input,
              com.google.protobuf.ExtensionRegistryLite extensionRegistry)
              throws com.google.protobuf.InvalidProtocolBufferException {
            return new Signature(input, extensionRegistry);
          }
        };

        public static com.google.protobuf.Parser<Signature> parser() {
          return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<Signature> getParserForType() {
          return PARSER;
        }

        @java.lang.Override
        public org.apache.calcite.avatica.proto.Common.Signature getDefaultInstanceForType() {
          return DEFAULT_INSTANCE;
        }

      }

}
