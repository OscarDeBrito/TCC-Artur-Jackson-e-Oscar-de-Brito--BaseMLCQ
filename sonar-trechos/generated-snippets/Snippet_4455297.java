public class Snippet__4455297 {

    public static final class Builder extends
            com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:exec.user.GetTablesReq)
            org.apache.drill.exec.proto.UserProtos.GetTablesReqOrBuilder {
          public static final com.google.protobuf.Descriptors.Descriptor
              getDescriptor() {
            return org.apache.drill.exec.proto.UserProtos.internal_static_exec_user_GetTablesReq_descriptor;
          }

          @java.lang.Override
          protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
              internalGetFieldAccessorTable() {
            return org.apache.drill.exec.proto.UserProtos.internal_static_exec_user_GetTablesReq_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                    org.apache.drill.exec.proto.UserProtos.GetTablesReq.class, org.apache.drill.exec.proto.UserProtos.GetTablesReq.Builder.class);
          }

          // Construct using org.apache.drill.exec.proto.UserProtos.GetTablesReq.newBuilder()
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
              getCatalogNameFilterFieldBuilder();
              getSchemaNameFilterFieldBuilder();
              getTableNameFilterFieldBuilder();
            }
          }
          @java.lang.Override
          public Builder clear() {
            super.clear();
            if (catalogNameFilterBuilder_ == null) {
              catalogNameFilter_ = null;
            } else {
              catalogNameFilterBuilder_.clear();
            }
            bitField0_ = (bitField0_ & ~0x00000001);
            if (schemaNameFilterBuilder_ == null) {
              schemaNameFilter_ = null;
            } else {
              schemaNameFilterBuilder_.clear();
            }
            bitField0_ = (bitField0_ & ~0x00000002);
            if (tableNameFilterBuilder_ == null) {
              tableNameFilter_ = null;
            } else {
              tableNameFilterBuilder_.clear();
            }
            bitField0_ = (bitField0_ & ~0x00000004);
            tableTypeFilter_ = com.google.protobuf.LazyStringArrayList.EMPTY;
            bitField0_ = (bitField0_ & ~0x00000008);
            return this;
          }

          @java.lang.Override
          public com.google.protobuf.Descriptors.Descriptor
              getDescriptorForType() {
            return org.apache.drill.exec.proto.UserProtos.internal_static_exec_user_GetTablesReq_descriptor;
          }

          @java.lang.Override
          public org.apache.drill.exec.proto.UserProtos.GetTablesReq getDefaultInstanceForType() {
            return org.apache.drill.exec.proto.UserProtos.GetTablesReq.getDefaultInstance();
          }

          @java.lang.Override
          public org.apache.drill.exec.proto.UserProtos.GetTablesReq build() {
            org.apache.drill.exec.proto.UserProtos.GetTablesReq result = buildPartial();
            if (!result.isInitialized()) {
              throw newUninitializedMessageException(result);
            }
            return result;
          }

          @java.lang.Override
          public org.apache.drill.exec.proto.UserProtos.GetTablesReq buildPartial() {
            org.apache.drill.exec.proto.UserProtos.GetTablesReq result = new org.apache.drill.exec.proto.UserProtos.GetTablesReq(this);
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
              to_bitField0_ |= 0x00000001;
            }
            if (catalogNameFilterBuilder_ == null) {
              result.catalogNameFilter_ = catalogNameFilter_;
            } else {
              result.catalogNameFilter_ = catalogNameFilterBuilder_.build();
            }
            if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
              to_bitField0_ |= 0x00000002;
            }
            if (schemaNameFilterBuilder_ == null) {
              result.schemaNameFilter_ = schemaNameFilter_;
            } else {
              result.schemaNameFilter_ = schemaNameFilterBuilder_.build();
            }
            if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
              to_bitField0_ |= 0x00000004;
            }
            if (tableNameFilterBuilder_ == null) {
              result.tableNameFilter_ = tableNameFilter_;
            } else {
              result.tableNameFilter_ = tableNameFilterBuilder_.build();
            }
            if (((bitField0_ & 0x00000008) == 0x00000008)) {
              tableTypeFilter_ = tableTypeFilter_.getUnmodifiableView();
              bitField0_ = (bitField0_ & ~0x00000008);
            }
            result.tableTypeFilter_ = tableTypeFilter_;
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
            if (other instanceof org.apache.drill.exec.proto.UserProtos.GetTablesReq) {
              return mergeFrom((org.apache.drill.exec.proto.UserProtos.GetTablesReq)other);
            } else {
              super.mergeFrom(other);
              return this;
            }
          }

          public Builder mergeFrom(org.apache.drill.exec.proto.UserProtos.GetTablesReq other) {
            if (other == org.apache.drill.exec.proto.UserProtos.GetTablesReq.getDefaultInstance()) return this;
            if (other.hasCatalogNameFilter()) {
              mergeCatalogNameFilter(other.getCatalogNameFilter());
            }
            if (other.hasSchemaNameFilter()) {
              mergeSchemaNameFilter(other.getSchemaNameFilter());
            }
            if (other.hasTableNameFilter()) {
              mergeTableNameFilter(other.getTableNameFilter());
            }
            if (!other.tableTypeFilter_.isEmpty()) {
              if (tableTypeFilter_.isEmpty()) {
                tableTypeFilter_ = other.tableTypeFilter_;
                bitField0_ = (bitField0_ & ~0x00000008);
              } else {
                ensureTableTypeFilterIsMutable();
                tableTypeFilter_.addAll(other.tableTypeFilter_);
              }
              onChanged();
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
            org.apache.drill.exec.proto.UserProtos.GetTablesReq parsedMessage = null;
            try {
              parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
              parsedMessage = (org.apache.drill.exec.proto.UserProtos.GetTablesReq) e.getUnfinishedMessage();
              throw e.unwrapIOException();
            } finally {
              if (parsedMessage != null) {
                mergeFrom(parsedMessage);
              }
            }
            return this;
          }
          private int bitField0_;

          private org.apache.drill.exec.proto.UserProtos.LikeFilter catalogNameFilter_ = null;
          private com.google.protobuf.SingleFieldBuilderV3<
              org.apache.drill.exec.proto.UserProtos.LikeFilter, org.apache.drill.exec.proto.UserProtos.LikeFilter.Builder, org.apache.drill.exec.proto.UserProtos.LikeFilterOrBuilder> catalogNameFilterBuilder_;
          /**
           * <code>optional .exec.user.LikeFilter catalog_name_filter = 1;</code>
           */
          public boolean hasCatalogNameFilter() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
          }
          /**
           * <code>optional .exec.user.LikeFilter catalog_name_filter = 1;</code>
           */
          public org.apache.drill.exec.proto.UserProtos.LikeFilter getCatalogNameFilter() {
            if (catalogNameFilterBuilder_ == null) {
              return catalogNameFilter_ == null ? org.apache.drill.exec.proto.UserProtos.LikeFilter.getDefaultInstance() : catalogNameFilter_;
            } else {
              return catalogNameFilterBuilder_.getMessage();
            }
          }
          /**
           * <code>optional .exec.user.LikeFilter catalog_name_filter = 1;</code>
           */
          public Builder setCatalogNameFilter(org.apache.drill.exec.proto.UserProtos.LikeFilter value) {
            if (catalogNameFilterBuilder_ == null) {
              if (value == null) {
                throw new NullPointerException();
              }
              catalogNameFilter_ = value;
              onChanged();
            } else {
              catalogNameFilterBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000001;
            return this;
          }
          /**
           * <code>optional .exec.user.LikeFilter catalog_name_filter = 1;</code>
           */
          public Builder setCatalogNameFilter(
              org.apache.drill.exec.proto.UserProtos.LikeFilter.Builder builderForValue) {
            if (catalogNameFilterBuilder_ == null) {
              catalogNameFilter_ = builderForValue.build();
              onChanged();
            } else {
              catalogNameFilterBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000001;
            return this;
          }
          /**
           * <code>optional .exec.user.LikeFilter catalog_name_filter = 1;</code>
           */
          public Builder mergeCatalogNameFilter(org.apache.drill.exec.proto.UserProtos.LikeFilter value) {
            if (catalogNameFilterBuilder_ == null) {
              if (((bitField0_ & 0x00000001) == 0x00000001) &&
                  catalogNameFilter_ != null &&
                  catalogNameFilter_ != org.apache.drill.exec.proto.UserProtos.LikeFilter.getDefaultInstance()) {
                catalogNameFilter_ =
                  org.apache.drill.exec.proto.UserProtos.LikeFilter.newBuilder(catalogNameFilter_).mergeFrom(value).buildPartial();
              } else {
                catalogNameFilter_ = value;
              }
              onChanged();
            } else {
              catalogNameFilterBuilder_.mergeFrom(value);
            }
            bitField0_ |= 0x00000001;
            return this;
          }
          /**
           * <code>optional .exec.user.LikeFilter catalog_name_filter = 1;</code>
           */
          public Builder clearCatalogNameFilter() {
            if (catalogNameFilterBuilder_ == null) {
              catalogNameFilter_ = null;
              onChanged();
            } else {
              catalogNameFilterBuilder_.clear();
            }
            bitField0_ = (bitField0_ & ~0x00000001);
            return this;
          }
          /**
           * <code>optional .exec.user.LikeFilter catalog_name_filter = 1;</code>
           */
          public org.apache.drill.exec.proto.UserProtos.LikeFilter.Builder getCatalogNameFilterBuilder() {
            bitField0_ |= 0x00000001;
            onChanged();
            return getCatalogNameFilterFieldBuilder().getBuilder();
          }
          /**
           * <code>optional .exec.user.LikeFilter catalog_name_filter = 1;</code>
           */
          public org.apache.drill.exec.proto.UserProtos.LikeFilterOrBuilder getCatalogNameFilterOrBuilder() {
            if (catalogNameFilterBuilder_ != null) {
              return catalogNameFilterBuilder_.getMessageOrBuilder();
            } else {
              return catalogNameFilter_ == null ?
                  org.apache.drill.exec.proto.UserProtos.LikeFilter.getDefaultInstance() : catalogNameFilter_;
            }
          }
          /**
           * <code>optional .exec.user.LikeFilter catalog_name_filter = 1;</code>
           */
          private com.google.protobuf.SingleFieldBuilderV3<
              org.apache.drill.exec.proto.UserProtos.LikeFilter, org.apache.drill.exec.proto.UserProtos.LikeFilter.Builder, org.apache.drill.exec.proto.UserProtos.LikeFilterOrBuilder> 
              getCatalogNameFilterFieldBuilder() {
            if (catalogNameFilterBuilder_ == null) {
              catalogNameFilterBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
                  org.apache.drill.exec.proto.UserProtos.LikeFilter, org.apache.drill.exec.proto.UserProtos.LikeFilter.Builder, org.apache.drill.exec.proto.UserProtos.LikeFilterOrBuilder>(
                      getCatalogNameFilter(),
                      getParentForChildren(),
                      isClean());
              catalogNameFilter_ = null;
            }
            return catalogNameFilterBuilder_;
          }

          private org.apache.drill.exec.proto.UserProtos.LikeFilter schemaNameFilter_ = null;
          private com.google.protobuf.SingleFieldBuilderV3<
              org.apache.drill.exec.proto.UserProtos.LikeFilter, org.apache.drill.exec.proto.UserProtos.LikeFilter.Builder, org.apache.drill.exec.proto.UserProtos.LikeFilterOrBuilder> schemaNameFilterBuilder_;
          /**
           * <code>optional .exec.user.LikeFilter schema_name_filter = 2;</code>
           */
          public boolean hasSchemaNameFilter() {
            return ((bitField0_ & 0x00000002) == 0x00000002);
          }
          /**
           * <code>optional .exec.user.LikeFilter schema_name_filter = 2;</code>
           */
          public org.apache.drill.exec.proto.UserProtos.LikeFilter getSchemaNameFilter() {
            if (schemaNameFilterBuilder_ == null) {
              return schemaNameFilter_ == null ? org.apache.drill.exec.proto.UserProtos.LikeFilter.getDefaultInstance() : schemaNameFilter_;
            } else {
              return schemaNameFilterBuilder_.getMessage();
            }
          }
          /**
           * <code>optional .exec.user.LikeFilter schema_name_filter = 2;</code>
           */
          public Builder setSchemaNameFilter(org.apache.drill.exec.proto.UserProtos.LikeFilter value) {
            if (schemaNameFilterBuilder_ == null) {
              if (value == null) {
                throw new NullPointerException();
              }
              schemaNameFilter_ = value;
              onChanged();
            } else {
              schemaNameFilterBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000002;
            return this;
          }
          /**
           * <code>optional .exec.user.LikeFilter schema_name_filter = 2;</code>
           */
          public Builder setSchemaNameFilter(
              org.apache.drill.exec.proto.UserProtos.LikeFilter.Builder builderForValue) {
            if (schemaNameFilterBuilder_ == null) {
              schemaNameFilter_ = builderForValue.build();
              onChanged();
            } else {
              schemaNameFilterBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000002;
            return this;
          }
          /**
           * <code>optional .exec.user.LikeFilter schema_name_filter = 2;</code>
           */
          public Builder mergeSchemaNameFilter(org.apache.drill.exec.proto.UserProtos.LikeFilter value) {
            if (schemaNameFilterBuilder_ == null) {
              if (((bitField0_ & 0x00000002) == 0x00000002) &&
                  schemaNameFilter_ != null &&
                  schemaNameFilter_ != org.apache.drill.exec.proto.UserProtos.LikeFilter.getDefaultInstance()) {
                schemaNameFilter_ =
                  org.apache.drill.exec.proto.UserProtos.LikeFilter.newBuilder(schemaNameFilter_).mergeFrom(value).buildPartial();
              } else {
                schemaNameFilter_ = value;
              }
              onChanged();
            } else {
              schemaNameFilterBuilder_.mergeFrom(value);
            }
            bitField0_ |= 0x00000002;
            return this;
          }
          /**
           * <code>optional .exec.user.LikeFilter schema_name_filter = 2;</code>
           */
          public Builder clearSchemaNameFilter() {
            if (schemaNameFilterBuilder_ == null) {
              schemaNameFilter_ = null;
              onChanged();
            } else {
              schemaNameFilterBuilder_.clear();
            }
            bitField0_ = (bitField0_ & ~0x00000002);
            return this;
          }
          /**
           * <code>optional .exec.user.LikeFilter schema_name_filter = 2;</code>
           */
          public org.apache.drill.exec.proto.UserProtos.LikeFilter.Builder getSchemaNameFilterBuilder() {
            bitField0_ |= 0x00000002;
            onChanged();
            return getSchemaNameFilterFieldBuilder().getBuilder();
          }
          /**
           * <code>optional .exec.user.LikeFilter schema_name_filter = 2;</code>
           */
          public org.apache.drill.exec.proto.UserProtos.LikeFilterOrBuilder getSchemaNameFilterOrBuilder() {
            if (schemaNameFilterBuilder_ != null) {
              return schemaNameFilterBuilder_.getMessageOrBuilder();
            } else {
              return schemaNameFilter_ == null ?
                  org.apache.drill.exec.proto.UserProtos.LikeFilter.getDefaultInstance() : schemaNameFilter_;
            }
          }
          /**
           * <code>optional .exec.user.LikeFilter schema_name_filter = 2;</code>
           */
          private com.google.protobuf.SingleFieldBuilderV3<
              org.apache.drill.exec.proto.UserProtos.LikeFilter, org.apache.drill.exec.proto.UserProtos.LikeFilter.Builder, org.apache.drill.exec.proto.UserProtos.LikeFilterOrBuilder> 
              getSchemaNameFilterFieldBuilder() {
            if (schemaNameFilterBuilder_ == null) {
              schemaNameFilterBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
                  org.apache.drill.exec.proto.UserProtos.LikeFilter, org.apache.drill.exec.proto.UserProtos.LikeFilter.Builder, org.apache.drill.exec.proto.UserProtos.LikeFilterOrBuilder>(
                      getSchemaNameFilter(),
                      getParentForChildren(),
                      isClean());
              schemaNameFilter_ = null;
            }
            return schemaNameFilterBuilder_;
          }

          private org.apache.drill.exec.proto.UserProtos.LikeFilter tableNameFilter_ = null;
          private com.google.protobuf.SingleFieldBuilderV3<
              org.apache.drill.exec.proto.UserProtos.LikeFilter, org.apache.drill.exec.proto.UserProtos.LikeFilter.Builder, org.apache.drill.exec.proto.UserProtos.LikeFilterOrBuilder> tableNameFilterBuilder_;
          /**
           * <code>optional .exec.user.LikeFilter table_name_filter = 3;</code>
           */
          public boolean hasTableNameFilter() {
            return ((bitField0_ & 0x00000004) == 0x00000004);
          }
          /**
           * <code>optional .exec.user.LikeFilter table_name_filter = 3;</code>
           */
          public org.apache.drill.exec.proto.UserProtos.LikeFilter getTableNameFilter() {
            if (tableNameFilterBuilder_ == null) {
              return tableNameFilter_ == null ? org.apache.drill.exec.proto.UserProtos.LikeFilter.getDefaultInstance() : tableNameFilter_;
            } else {
              return tableNameFilterBuilder_.getMessage();
            }
          }
          /**
           * <code>optional .exec.user.LikeFilter table_name_filter = 3;</code>
           */
          public Builder setTableNameFilter(org.apache.drill.exec.proto.UserProtos.LikeFilter value) {
            if (tableNameFilterBuilder_ == null) {
              if (value == null) {
                throw new NullPointerException();
              }
              tableNameFilter_ = value;
              onChanged();
            } else {
              tableNameFilterBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000004;
            return this;
          }
          /**
           * <code>optional .exec.user.LikeFilter table_name_filter = 3;</code>
           */
          public Builder setTableNameFilter(
              org.apache.drill.exec.proto.UserProtos.LikeFilter.Builder builderForValue) {
            if (tableNameFilterBuilder_ == null) {
              tableNameFilter_ = builderForValue.build();
              onChanged();
            } else {
              tableNameFilterBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000004;
            return this;
          }
          /**
           * <code>optional .exec.user.LikeFilter table_name_filter = 3;</code>
           */
          public Builder mergeTableNameFilter(org.apache.drill.exec.proto.UserProtos.LikeFilter value) {
            if (tableNameFilterBuilder_ == null) {
              if (((bitField0_ & 0x00000004) == 0x00000004) &&
                  tableNameFilter_ != null &&
                  tableNameFilter_ != org.apache.drill.exec.proto.UserProtos.LikeFilter.getDefaultInstance()) {
                tableNameFilter_ =
                  org.apache.drill.exec.proto.UserProtos.LikeFilter.newBuilder(tableNameFilter_).mergeFrom(value).buildPartial();
              } else {
                tableNameFilter_ = value;
              }
              onChanged();
            } else {
              tableNameFilterBuilder_.mergeFrom(value);
            }
            bitField0_ |= 0x00000004;
            return this;
          }
          /**
           * <code>optional .exec.user.LikeFilter table_name_filter = 3;</code>
           */
          public Builder clearTableNameFilter() {
            if (tableNameFilterBuilder_ == null) {
              tableNameFilter_ = null;
              onChanged();
            } else {
              tableNameFilterBuilder_.clear();
            }
            bitField0_ = (bitField0_ & ~0x00000004);
            return this;
          }
          /**
           * <code>optional .exec.user.LikeFilter table_name_filter = 3;</code>
           */
          public org.apache.drill.exec.proto.UserProtos.LikeFilter.Builder getTableNameFilterBuilder() {
            bitField0_ |= 0x00000004;
            onChanged();
            return getTableNameFilterFieldBuilder().getBuilder();
          }
          /**
           * <code>optional .exec.user.LikeFilter table_name_filter = 3;</code>
           */
          public org.apache.drill.exec.proto.UserProtos.LikeFilterOrBuilder getTableNameFilterOrBuilder() {
            if (tableNameFilterBuilder_ != null) {
              return tableNameFilterBuilder_.getMessageOrBuilder();
            } else {
              return tableNameFilter_ == null ?
                  org.apache.drill.exec.proto.UserProtos.LikeFilter.getDefaultInstance() : tableNameFilter_;
            }
          }
          /**
           * <code>optional .exec.user.LikeFilter table_name_filter = 3;</code>
           */
          private com.google.protobuf.SingleFieldBuilderV3<
              org.apache.drill.exec.proto.UserProtos.LikeFilter, org.apache.drill.exec.proto.UserProtos.LikeFilter.Builder, org.apache.drill.exec.proto.UserProtos.LikeFilterOrBuilder> 
              getTableNameFilterFieldBuilder() {
            if (tableNameFilterBuilder_ == null) {
              tableNameFilterBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
                  org.apache.drill.exec.proto.UserProtos.LikeFilter, org.apache.drill.exec.proto.UserProtos.LikeFilter.Builder, org.apache.drill.exec.proto.UserProtos.LikeFilterOrBuilder>(
                      getTableNameFilter(),
                      getParentForChildren(),
                      isClean());
              tableNameFilter_ = null;
            }
            return tableNameFilterBuilder_;
          }

          private com.google.protobuf.LazyStringList tableTypeFilter_ = com.google.protobuf.LazyStringArrayList.EMPTY;
          private void ensureTableTypeFilterIsMutable() {
            if (!((bitField0_ & 0x00000008) == 0x00000008)) {
              tableTypeFilter_ = new com.google.protobuf.LazyStringArrayList(tableTypeFilter_);
              bitField0_ |= 0x00000008;
             }
          }
          /**
           * <code>repeated string table_type_filter = 4;</code>
           */
          public com.google.protobuf.ProtocolStringList
              getTableTypeFilterList() {
            return tableTypeFilter_.getUnmodifiableView();
          }
          /**
           * <code>repeated string table_type_filter = 4;</code>
           */
          public int getTableTypeFilterCount() {
            return tableTypeFilter_.size();
          }
          /**
           * <code>repeated string table_type_filter = 4;</code>
           */
          public java.lang.String getTableTypeFilter(int index) {
            return tableTypeFilter_.get(index);
          }
          /**
           * <code>repeated string table_type_filter = 4;</code>
           */
          public com.google.protobuf.ByteString
              getTableTypeFilterBytes(int index) {
            return tableTypeFilter_.getByteString(index);
          }
          /**
           * <code>repeated string table_type_filter = 4;</code>
           */
          public Builder setTableTypeFilter(
              int index, java.lang.String value) {
            if (value == null) {
        throw new NullPointerException();
      }
      ensureTableTypeFilterIsMutable();
            tableTypeFilter_.set(index, value);
            onChanged();
            return this;
          }
          /**
           * <code>repeated string table_type_filter = 4;</code>
           */
          public Builder addTableTypeFilter(
              java.lang.String value) {
            if (value == null) {
        throw new NullPointerException();
      }
      ensureTableTypeFilterIsMutable();
            tableTypeFilter_.add(value);
            onChanged();
            return this;
          }
          /**
           * <code>repeated string table_type_filter = 4;</code>
           */
          public Builder addAllTableTypeFilter(
              java.lang.Iterable<java.lang.String> values) {
            ensureTableTypeFilterIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(
                values, tableTypeFilter_);
            onChanged();
            return this;
          }
          /**
           * <code>repeated string table_type_filter = 4;</code>
           */
          public Builder clearTableTypeFilter() {
            tableTypeFilter_ = com.google.protobuf.LazyStringArrayList.EMPTY;
            bitField0_ = (bitField0_ & ~0x00000008);
            onChanged();
            return this;
          }
          /**
           * <code>repeated string table_type_filter = 4;</code>
           */
          public Builder addTableTypeFilterBytes(
              com.google.protobuf.ByteString value) {
            if (value == null) {
        throw new NullPointerException();
      }
      ensureTableTypeFilterIsMutable();
            tableTypeFilter_.add(value);
            onChanged();
            return this;
          }
          @java.lang.Override
          public final Builder setUnknownFields(
              final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.setUnknownFields(unknownFields);
          }

          @java.lang.Override
          public final Builder mergeUnknownFields(
              final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
          }


          // @@protoc_insertion_point(builder_scope:exec.user.GetTablesReq)
        }

}
