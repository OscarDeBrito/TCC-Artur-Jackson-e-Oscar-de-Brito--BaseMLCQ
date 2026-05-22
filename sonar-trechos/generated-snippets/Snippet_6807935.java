public class Snippet__6807935 {

    public static final class Builder extends
            com.google.protobuf.GeneratedMessage.Builder<Builder>
           implements com.google.javascript.jscomp.FunctionInformationMap.ModuleOrBuilder {
          public static final com.google.protobuf.Descriptors.Descriptor
              getDescriptor() {
            return com.google.javascript.jscomp.FunctionInfo.internal_static_jscomp_FunctionInformationMap_Module_descriptor;
          }

          protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
              internalGetFieldAccessorTable() {
            return com.google.javascript.jscomp.FunctionInfo.internal_static_jscomp_FunctionInformationMap_Module_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                    com.google.javascript.jscomp.FunctionInformationMap.Module.class, com.google.javascript.jscomp.FunctionInformationMap.Module.Builder.class);
          }

          // Construct using com.google.javascript.jscomp.FunctionInformationMap.Module.newBuilder()
          private Builder() {
            maybeForceBuilderInitialization();
          }

          private Builder(
              com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
          }
          private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
            }
          }
          private static Builder create() {
            return new Builder();
          }

          public Builder clear() {
            super.clear();
            name_ = "";
            bitField0_ = (bitField0_ & ~0x00000001);
            compiledSource_ = "";
            bitField0_ = (bitField0_ & ~0x00000002);
            return this;
          }

          public Builder clone() {
            return create().mergeFrom(buildPartial());
          }

          public com.google.protobuf.Descriptors.Descriptor
              getDescriptorForType() {
            return com.google.javascript.jscomp.FunctionInfo.internal_static_jscomp_FunctionInformationMap_Module_descriptor;
          }

          public com.google.javascript.jscomp.FunctionInformationMap.Module getDefaultInstanceForType() {
            return com.google.javascript.jscomp.FunctionInformationMap.Module.getDefaultInstance();
          }

          public com.google.javascript.jscomp.FunctionInformationMap.Module build() {
            com.google.javascript.jscomp.FunctionInformationMap.Module result = buildPartial();
            if (!result.isInitialized()) {
              throw newUninitializedMessageException(result);
            }
            return result;
          }

          public com.google.javascript.jscomp.FunctionInformationMap.Module buildPartial() {
            com.google.javascript.jscomp.FunctionInformationMap.Module result = new com.google.javascript.jscomp.FunctionInformationMap.Module(this);
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
              to_bitField0_ |= 0x00000001;
            }
            result.name_ = name_;
            if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
              to_bitField0_ |= 0x00000002;
            }
            result.compiledSource_ = compiledSource_;
            result.bitField0_ = to_bitField0_;
            onBuilt();
            return result;
          }

          public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof com.google.javascript.jscomp.FunctionInformationMap.Module) {
              return mergeFrom((com.google.javascript.jscomp.FunctionInformationMap.Module)other);
            } else {
              super.mergeFrom(other);
              return this;
            }
          }

          public Builder mergeFrom(com.google.javascript.jscomp.FunctionInformationMap.Module other) {
            if (other == com.google.javascript.jscomp.FunctionInformationMap.Module.getDefaultInstance()) return this;
            if (other.hasName()) {
              bitField0_ |= 0x00000001;
              name_ = other.name_;
              onChanged();
            }
            if (other.hasCompiledSource()) {
              bitField0_ |= 0x00000002;
              compiledSource_ = other.compiledSource_;
              onChanged();
            }
            this.mergeUnknownFields(other.getUnknownFields());
            return this;
          }

          public final boolean isInitialized() {
            if (!hasName()) {
          
              return false;
            }
            if (!hasCompiledSource()) {
          
              return false;
            }
            return true;
          }

          public Builder mergeFrom(
              com.google.protobuf.CodedInputStream input,
              com.google.protobuf.ExtensionRegistryLite extensionRegistry)
              throws java.io.IOException {
            com.google.javascript.jscomp.FunctionInformationMap.Module parsedMessage = null;
            try {
              parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
              parsedMessage = (com.google.javascript.jscomp.FunctionInformationMap.Module) e.getUnfinishedMessage();
              throw e;
            } finally {
              if (parsedMessage != null) {
                mergeFrom(parsedMessage);
              }
            }
            return this;
          }
          private int bitField0_;

          // required string name = 102;
          private java.lang.Object name_ = "";
          /**
           * <code>required string name = 102;</code>
           */
          public boolean hasName() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
          }
          /**
           * <code>required string name = 102;</code>
           */
          public java.lang.String getName() {
            java.lang.Object ref = name_;
            if (!(ref instanceof java.lang.String)) {
              java.lang.String s = ((com.google.protobuf.ByteString) ref)
                  .toStringUtf8();
              name_ = s;
              return s;
            } else {
              return (java.lang.String) ref;
            }
          }
          /**
           * <code>required string name = 102;</code>
           */
          public com.google.protobuf.ByteString
              getNameBytes() {
            java.lang.Object ref = name_;
            if (ref instanceof String) {
              com.google.protobuf.ByteString b = 
                  com.google.protobuf.ByteString.copyFromUtf8(
                      (java.lang.String) ref);
              name_ = b;
              return b;
            } else {
              return (com.google.protobuf.ByteString) ref;
            }
          }
          /**
           * <code>required string name = 102;</code>
           */
          public Builder setName(
              java.lang.String value) {
            if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000001;
            name_ = value;
            onChanged();
            return this;
          }
          /**
           * <code>required string name = 102;</code>
           */
          public Builder clearName() {
            bitField0_ = (bitField0_ & ~0x00000001);
            name_ = getDefaultInstance().getName();
            onChanged();
            return this;
          }
          /**
           * <code>required string name = 102;</code>
           */
          public Builder setNameBytes(
              com.google.protobuf.ByteString value) {
            if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000001;
            name_ = value;
            onChanged();
            return this;
          }

          // required string compiled_source = 103;
          private java.lang.Object compiledSource_ = "";
          /**
           * <code>required string compiled_source = 103;</code>
           */
          public boolean hasCompiledSource() {
            return ((bitField0_ & 0x00000002) == 0x00000002);
          }
          /**
           * <code>required string compiled_source = 103;</code>
           */
          public java.lang.String getCompiledSource() {
            java.lang.Object ref = compiledSource_;
            if (!(ref instanceof java.lang.String)) {
              java.lang.String s = ((com.google.protobuf.ByteString) ref)
                  .toStringUtf8();
              compiledSource_ = s;
              return s;
            } else {
              return (java.lang.String) ref;
            }
          }
          /**
           * <code>required string compiled_source = 103;</code>
           */
          public com.google.protobuf.ByteString
              getCompiledSourceBytes() {
            java.lang.Object ref = compiledSource_;
            if (ref instanceof String) {
              com.google.protobuf.ByteString b = 
                  com.google.protobuf.ByteString.copyFromUtf8(
                      (java.lang.String) ref);
              compiledSource_ = b;
              return b;
            } else {
              return (com.google.protobuf.ByteString) ref;
            }
          }
          /**
           * <code>required string compiled_source = 103;</code>
           */
          public Builder setCompiledSource(
              java.lang.String value) {
            if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000002;
            compiledSource_ = value;
            onChanged();
            return this;
          }
          /**
           * <code>required string compiled_source = 103;</code>
           */
          public Builder clearCompiledSource() {
            bitField0_ = (bitField0_ & ~0x00000002);
            compiledSource_ = getDefaultInstance().getCompiledSource();
            onChanged();
            return this;
          }
          /**
           * <code>required string compiled_source = 103;</code>
           */
          public Builder setCompiledSourceBytes(
              com.google.protobuf.ByteString value) {
            if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000002;
            compiledSource_ = value;
            onChanged();
            return this;
          }

          // @@protoc_insertion_point(builder_scope:jscomp.FunctionInformationMap.Module)
        }

}
