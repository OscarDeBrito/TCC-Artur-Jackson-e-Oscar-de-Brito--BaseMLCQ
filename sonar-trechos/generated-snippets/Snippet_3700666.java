public class Snippet__3700666 {

    public static final class Builder extends
            com.google.protobuf.GeneratedMessage.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:com.alibaba.otter.canal.protocol.RowChange)
            RowChangeOrBuilder {
          public static final com.google.protobuf.Descriptors.Descriptor
              getDescriptor() {
            return CanalEntry.internal_static_com_alibaba_otter_canal_protocol_RowChange_descriptor;
          }

          protected FieldAccessorTable
              internalGetFieldAccessorTable() {
            return CanalEntry.internal_static_com_alibaba_otter_canal_protocol_RowChange_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                    RowChange.class, Builder.class);
          }

          // Construct using com.alibaba.otter.canal.protocol.CanalEntry.RowChange.newBuilder()
          private Builder() {
            maybeForceBuilderInitialization();
          }

          private Builder(
              BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
          }
          private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
              getRowDatasFieldBuilder();
              getPropsFieldBuilder();
            }
          }
          private static Builder create() {
            return new Builder();
          }

          public Builder clear() {
            super.clear();
            tableId_ = 0L;
            bitField0_ = (bitField0_ & ~0x00000001);
            eventType_ = EventType.UPDATE;
            bitField0_ = (bitField0_ & ~0x00000002);
            isDdl_ = false;
            bitField0_ = (bitField0_ & ~0x00000004);
            sql_ = "";
            bitField0_ = (bitField0_ & ~0x00000008);
            if (rowDatasBuilder_ == null) {
              rowDatas_ = java.util.Collections.emptyList();
              bitField0_ = (bitField0_ & ~0x00000010);
            } else {
              rowDatasBuilder_.clear();
            }
            if (propsBuilder_ == null) {
              props_ = java.util.Collections.emptyList();
              bitField0_ = (bitField0_ & ~0x00000020);
            } else {
              propsBuilder_.clear();
            }
            ddlSchemaName_ = "";
            bitField0_ = (bitField0_ & ~0x00000040);
            return this;
          }

          public Builder clone() {
            return create().mergeFrom(buildPartial());
          }

          public com.google.protobuf.Descriptors.Descriptor
              getDescriptorForType() {
            return CanalEntry.internal_static_com_alibaba_otter_canal_protocol_RowChange_descriptor;
          }

          public RowChange getDefaultInstanceForType() {
            return RowChange.getDefaultInstance();
          }

          public RowChange build() {
            RowChange result = buildPartial();
            if (!result.isInitialized()) {
              throw newUninitializedMessageException(result);
            }
            return result;
          }

          public RowChange buildPartial() {
            RowChange result = new RowChange(this);
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
              to_bitField0_ |= 0x00000001;
            }
            result.tableId_ = tableId_;
            if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
              to_bitField0_ |= 0x00000002;
            }
            result.eventType_ = eventType_;
            if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
              to_bitField0_ |= 0x00000004;
            }
            result.isDdl_ = isDdl_;
            if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
              to_bitField0_ |= 0x00000008;
            }
            result.sql_ = sql_;
            if (rowDatasBuilder_ == null) {
              if (((bitField0_ & 0x00000010) == 0x00000010)) {
                rowDatas_ = java.util.Collections.unmodifiableList(rowDatas_);
                bitField0_ = (bitField0_ & ~0x00000010);
              }
              result.rowDatas_ = rowDatas_;
            } else {
              result.rowDatas_ = rowDatasBuilder_.build();
            }
            if (propsBuilder_ == null) {
              if (((bitField0_ & 0x00000020) == 0x00000020)) {
                props_ = java.util.Collections.unmodifiableList(props_);
                bitField0_ = (bitField0_ & ~0x00000020);
              }
              result.props_ = props_;
            } else {
              result.props_ = propsBuilder_.build();
            }
            if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
              to_bitField0_ |= 0x00000010;
            }
            result.ddlSchemaName_ = ddlSchemaName_;
            result.bitField0_ = to_bitField0_;
            onBuilt();
            return result;
          }

          public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof RowChange) {
              return mergeFrom((RowChange)other);
            } else {
              super.mergeFrom(other);
              return this;
            }
          }

          public Builder mergeFrom(RowChange other) {
            if (other == RowChange.getDefaultInstance()) return this;
            if (other.hasTableId()) {
              setTableId(other.getTableId());
            }
            if (other.hasEventType()) {
              setEventType(other.getEventType());
            }
            if (other.hasIsDdl()) {
              setIsDdl(other.getIsDdl());
            }
            if (other.hasSql()) {
              bitField0_ |= 0x00000008;
              sql_ = other.sql_;
              onChanged();
            }
            if (rowDatasBuilder_ == null) {
              if (!other.rowDatas_.isEmpty()) {
                if (rowDatas_.isEmpty()) {
                  rowDatas_ = other.rowDatas_;
                  bitField0_ = (bitField0_ & ~0x00000010);
                } else {
                  ensureRowDatasIsMutable();
                  rowDatas_.addAll(other.rowDatas_);
                }
                onChanged();
              }
            } else {
              if (!other.rowDatas_.isEmpty()) {
                if (rowDatasBuilder_.isEmpty()) {
                  rowDatasBuilder_.dispose();
                  rowDatasBuilder_ = null;
                  rowDatas_ = other.rowDatas_;
                  bitField0_ = (bitField0_ & ~0x00000010);
                  rowDatasBuilder_ =
                    com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                       getRowDatasFieldBuilder() : null;
                } else {
                  rowDatasBuilder_.addAllMessages(other.rowDatas_);
                }
              }
            }
            if (propsBuilder_ == null) {
              if (!other.props_.isEmpty()) {
                if (props_.isEmpty()) {
                  props_ = other.props_;
                  bitField0_ = (bitField0_ & ~0x00000020);
                } else {
                  ensurePropsIsMutable();
                  props_.addAll(other.props_);
                }
                onChanged();
              }
            } else {
              if (!other.props_.isEmpty()) {
                if (propsBuilder_.isEmpty()) {
                  propsBuilder_.dispose();
                  propsBuilder_ = null;
                  props_ = other.props_;
                  bitField0_ = (bitField0_ & ~0x00000020);
                  propsBuilder_ =
                    com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                       getPropsFieldBuilder() : null;
                } else {
                  propsBuilder_.addAllMessages(other.props_);
                }
              }
            }
            if (other.hasDdlSchemaName()) {
              bitField0_ |= 0x00000040;
              ddlSchemaName_ = other.ddlSchemaName_;
              onChanged();
            }
            this.mergeUnknownFields(other.getUnknownFields());
            return this;
          }

          public final boolean isInitialized() {
            return true;
          }

          public Builder mergeFrom(
              com.google.protobuf.CodedInputStream input,
              com.google.protobuf.ExtensionRegistryLite extensionRegistry)
              throws java.io.IOException {
            RowChange parsedMessage = null;
            try {
              parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
              parsedMessage = (RowChange) e.getUnfinishedMessage();
              throw e;
            } finally {
              if (parsedMessage != null) {
                mergeFrom(parsedMessage);
              }
            }
            return this;
          }
          private int bitField0_;

          private long tableId_ ;
          /**
           * <code>optional int64 tableId = 1;</code>
           *
           * <pre>
           **tableId,由数据库产生*
           * </pre>
           */
          public boolean hasTableId() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
          }
          /**
           * <code>optional int64 tableId = 1;</code>
           *
           * <pre>
           **tableId,由数据库产生*
           * </pre>
           */
          public long getTableId() {
            return tableId_;
          }
          /**
           * <code>optional int64 tableId = 1;</code>
           *
           * <pre>
           **tableId,由数据库产生*
           * </pre>
           */
          public Builder setTableId(long value) {
            bitField0_ |= 0x00000001;
            tableId_ = value;
            onChanged();
            return this;
          }
          /**
           * <code>optional int64 tableId = 1;</code>
           *
           * <pre>
           **tableId,由数据库产生*
           * </pre>
           */
          public Builder clearTableId() {
            bitField0_ = (bitField0_ & ~0x00000001);
            tableId_ = 0L;
            onChanged();
            return this;
          }

          private EventType eventType_ = EventType.UPDATE;
          /**
           * <code>optional .com.alibaba.otter.canal.protocol.EventType eventType = 2 [default = UPDATE];</code>
           *
           * <pre>
           **数据变更类型*
           * </pre>
           */
          public boolean hasEventType() {
            return ((bitField0_ & 0x00000002) == 0x00000002);
          }
          /**
           * <code>optional .com.alibaba.otter.canal.protocol.EventType eventType = 2 [default = UPDATE];</code>
           *
           * <pre>
           **数据变更类型*
           * </pre>
           */
          public EventType getEventType() {
            return eventType_;
          }
          /**
           * <code>optional .com.alibaba.otter.canal.protocol.EventType eventType = 2 [default = UPDATE];</code>
           *
           * <pre>
           **数据变更类型*
           * </pre>
           */
          public Builder setEventType(EventType value) {
            if (value == null) {
              throw new NullPointerException();
            }
            bitField0_ |= 0x00000002;
            eventType_ = value;
            onChanged();
            return this;
          }
          /**
           * <code>optional .com.alibaba.otter.canal.protocol.EventType eventType = 2 [default = UPDATE];</code>
           *
           * <pre>
           **数据变更类型*
           * </pre>
           */
          public Builder clearEventType() {
            bitField0_ = (bitField0_ & ~0x00000002);
            eventType_ = EventType.UPDATE;
            onChanged();
            return this;
          }

          private boolean isDdl_ ;
          /**
           * <code>optional bool isDdl = 10 [default = false];</code>
           *
           * <pre>
           ** 标识是否是ddl语句  *
           * </pre>
           */
          public boolean hasIsDdl() {
            return ((bitField0_ & 0x00000004) == 0x00000004);
          }
          /**
           * <code>optional bool isDdl = 10 [default = false];</code>
           *
           * <pre>
           ** 标识是否是ddl语句  *
           * </pre>
           */
          public boolean getIsDdl() {
            return isDdl_;
          }
          /**
           * <code>optional bool isDdl = 10 [default = false];</code>
           *
           * <pre>
           ** 标识是否是ddl语句  *
           * </pre>
           */
          public Builder setIsDdl(boolean value) {
            bitField0_ |= 0x00000004;
            isDdl_ = value;
            onChanged();
            return this;
          }
          /**
           * <code>optional bool isDdl = 10 [default = false];</code>
           *
           * <pre>
           ** 标识是否是ddl语句  *
           * </pre>
           */
          public Builder clearIsDdl() {
            bitField0_ = (bitField0_ & ~0x00000004);
            isDdl_ = false;
            onChanged();
            return this;
          }

          private Object sql_ = "";
          /**
           * <code>optional string sql = 11;</code>
           *
           * <pre>
           ** ddl/query的sql语句  *
           * </pre>
           */
          public boolean hasSql() {
            return ((bitField0_ & 0x00000008) == 0x00000008);
          }
          /**
           * <code>optional string sql = 11;</code>
           *
           * <pre>
           ** ddl/query的sql语句  *
           * </pre>
           */
          public String getSql() {
            Object ref = sql_;
            if (!(ref instanceof String)) {
              com.google.protobuf.ByteString bs =
                  (com.google.protobuf.ByteString) ref;
              String s = bs.toStringUtf8();
              if (bs.isValidUtf8()) {
                sql_ = s;
              }
              return s;
            } else {
              return (String) ref;
            }
          }
          /**
           * <code>optional string sql = 11;</code>
           *
           * <pre>
           ** ddl/query的sql语句  *
           * </pre>
           */
          public com.google.protobuf.ByteString
              getSqlBytes() {
            Object ref = sql_;
            if (ref instanceof String) {
              com.google.protobuf.ByteString b =
                  com.google.protobuf.ByteString.copyFromUtf8(
                      (String) ref);
              sql_ = b;
              return b;
            } else {
              return (com.google.protobuf.ByteString) ref;
            }
          }
          /**
           * <code>optional string sql = 11;</code>
           *
           * <pre>
           ** ddl/query的sql语句  *
           * </pre>
           */
          public Builder setSql(
              String value) {
            if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000008;
            sql_ = value;
            onChanged();
            return this;
          }
          /**
           * <code>optional string sql = 11;</code>
           *
           * <pre>
           ** ddl/query的sql语句  *
           * </pre>
           */
          public Builder clearSql() {
            bitField0_ = (bitField0_ & ~0x00000008);
            sql_ = getDefaultInstance().getSql();
            onChanged();
            return this;
          }
          /**
           * <code>optional string sql = 11;</code>
           *
           * <pre>
           ** ddl/query的sql语句  *
           * </pre>
           */
          public Builder setSqlBytes(
              com.google.protobuf.ByteString value) {
            if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000008;
            sql_ = value;
            onChanged();
            return this;
          }

          private java.util.List<RowData> rowDatas_ =
            java.util.Collections.emptyList();
          private void ensureRowDatasIsMutable() {
            if (!((bitField0_ & 0x00000010) == 0x00000010)) {
              rowDatas_ = new java.util.ArrayList<RowData>(rowDatas_);
              bitField0_ |= 0x00000010;
             }
          }

          private com.google.protobuf.RepeatedFieldBuilder<
              RowData, RowData.Builder, RowDataOrBuilder> rowDatasBuilder_;

          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public java.util.List<RowData> getRowDatasList() {
            if (rowDatasBuilder_ == null) {
              return java.util.Collections.unmodifiableList(rowDatas_);
            } else {
              return rowDatasBuilder_.getMessageList();
            }
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public int getRowDatasCount() {
            if (rowDatasBuilder_ == null) {
              return rowDatas_.size();
            } else {
              return rowDatasBuilder_.getCount();
            }
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public RowData getRowDatas(int index) {
            if (rowDatasBuilder_ == null) {
              return rowDatas_.get(index);
            } else {
              return rowDatasBuilder_.getMessage(index);
            }
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public Builder setRowDatas(
              int index, RowData value) {
            if (rowDatasBuilder_ == null) {
              if (value == null) {
                throw new NullPointerException();
              }
              ensureRowDatasIsMutable();
              rowDatas_.set(index, value);
              onChanged();
            } else {
              rowDatasBuilder_.setMessage(index, value);
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public Builder setRowDatas(
              int index, RowData.Builder builderForValue) {
            if (rowDatasBuilder_ == null) {
              ensureRowDatasIsMutable();
              rowDatas_.set(index, builderForValue.build());
              onChanged();
            } else {
              rowDatasBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public Builder addRowDatas(RowData value) {
            if (rowDatasBuilder_ == null) {
              if (value == null) {
                throw new NullPointerException();
              }
              ensureRowDatasIsMutable();
              rowDatas_.add(value);
              onChanged();
            } else {
              rowDatasBuilder_.addMessage(value);
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public Builder addRowDatas(
              int index, RowData value) {
            if (rowDatasBuilder_ == null) {
              if (value == null) {
                throw new NullPointerException();
              }
              ensureRowDatasIsMutable();
              rowDatas_.add(index, value);
              onChanged();
            } else {
              rowDatasBuilder_.addMessage(index, value);
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public Builder addRowDatas(
              RowData.Builder builderForValue) {
            if (rowDatasBuilder_ == null) {
              ensureRowDatasIsMutable();
              rowDatas_.add(builderForValue.build());
              onChanged();
            } else {
              rowDatasBuilder_.addMessage(builderForValue.build());
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public Builder addRowDatas(
              int index, RowData.Builder builderForValue) {
            if (rowDatasBuilder_ == null) {
              ensureRowDatasIsMutable();
              rowDatas_.add(index, builderForValue.build());
              onChanged();
            } else {
              rowDatasBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public Builder addAllRowDatas(
              Iterable<? extends RowData> values) {
            if (rowDatasBuilder_ == null) {
              ensureRowDatasIsMutable();
              com.google.protobuf.AbstractMessageLite.Builder.addAll(
                  values, rowDatas_);
              onChanged();
            } else {
              rowDatasBuilder_.addAllMessages(values);
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public Builder clearRowDatas() {
            if (rowDatasBuilder_ == null) {
              rowDatas_ = java.util.Collections.emptyList();
              bitField0_ = (bitField0_ & ~0x00000010);
              onChanged();
            } else {
              rowDatasBuilder_.clear();
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public Builder removeRowDatas(int index) {
            if (rowDatasBuilder_ == null) {
              ensureRowDatasIsMutable();
              rowDatas_.remove(index);
              onChanged();
            } else {
              rowDatasBuilder_.remove(index);
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public RowData.Builder getRowDatasBuilder(
              int index) {
            return getRowDatasFieldBuilder().getBuilder(index);
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public RowDataOrBuilder getRowDatasOrBuilder(
              int index) {
            if (rowDatasBuilder_ == null) {
              return rowDatas_.get(index);  } else {
              return rowDatasBuilder_.getMessageOrBuilder(index);
            }
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public java.util.List<? extends RowDataOrBuilder>
               getRowDatasOrBuilderList() {
            if (rowDatasBuilder_ != null) {
              return rowDatasBuilder_.getMessageOrBuilderList();
            } else {
              return java.util.Collections.unmodifiableList(rowDatas_);
            }
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public RowData.Builder addRowDatasBuilder() {
            return getRowDatasFieldBuilder().addBuilder(
                RowData.getDefaultInstance());
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public RowData.Builder addRowDatasBuilder(
              int index) {
            return getRowDatasFieldBuilder().addBuilder(
                index, RowData.getDefaultInstance());
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.RowData rowDatas = 12;</code>
           *
           * <pre>
           ** 一次数据库变更可能存在多行  *
           * </pre>
           */
          public java.util.List<RowData.Builder>
               getRowDatasBuilderList() {
            return getRowDatasFieldBuilder().getBuilderList();
          }
          private com.google.protobuf.RepeatedFieldBuilder<
              RowData, RowData.Builder, RowDataOrBuilder>
              getRowDatasFieldBuilder() {
            if (rowDatasBuilder_ == null) {
              rowDatasBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
                  RowData, RowData.Builder, RowDataOrBuilder>(
                      rowDatas_,
                      ((bitField0_ & 0x00000010) == 0x00000010),
                      getParentForChildren(),
                      isClean());
              rowDatas_ = null;
            }
            return rowDatasBuilder_;
          }

          private java.util.List<Pair> props_ =
            java.util.Collections.emptyList();
          private void ensurePropsIsMutable() {
            if (!((bitField0_ & 0x00000020) == 0x00000020)) {
              props_ = new java.util.ArrayList<Pair>(props_);
              bitField0_ |= 0x00000020;
             }
          }

          private com.google.protobuf.RepeatedFieldBuilder<
              Pair, Pair.Builder, PairOrBuilder> propsBuilder_;

          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public java.util.List<Pair> getPropsList() {
            if (propsBuilder_ == null) {
              return java.util.Collections.unmodifiableList(props_);
            } else {
              return propsBuilder_.getMessageList();
            }
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public int getPropsCount() {
            if (propsBuilder_ == null) {
              return props_.size();
            } else {
              return propsBuilder_.getCount();
            }
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public Pair getProps(int index) {
            if (propsBuilder_ == null) {
              return props_.get(index);
            } else {
              return propsBuilder_.getMessage(index);
            }
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public Builder setProps(
              int index, Pair value) {
            if (propsBuilder_ == null) {
              if (value == null) {
                throw new NullPointerException();
              }
              ensurePropsIsMutable();
              props_.set(index, value);
              onChanged();
            } else {
              propsBuilder_.setMessage(index, value);
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public Builder setProps(
              int index, Pair.Builder builderForValue) {
            if (propsBuilder_ == null) {
              ensurePropsIsMutable();
              props_.set(index, builderForValue.build());
              onChanged();
            } else {
              propsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public Builder addProps(Pair value) {
            if (propsBuilder_ == null) {
              if (value == null) {
                throw new NullPointerException();
              }
              ensurePropsIsMutable();
              props_.add(value);
              onChanged();
            } else {
              propsBuilder_.addMessage(value);
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public Builder addProps(
              int index, Pair value) {
            if (propsBuilder_ == null) {
              if (value == null) {
                throw new NullPointerException();
              }
              ensurePropsIsMutable();
              props_.add(index, value);
              onChanged();
            } else {
              propsBuilder_.addMessage(index, value);
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public Builder addProps(
              Pair.Builder builderForValue) {
            if (propsBuilder_ == null) {
              ensurePropsIsMutable();
              props_.add(builderForValue.build());
              onChanged();
            } else {
              propsBuilder_.addMessage(builderForValue.build());
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public Builder addProps(
              int index, Pair.Builder builderForValue) {
            if (propsBuilder_ == null) {
              ensurePropsIsMutable();
              props_.add(index, builderForValue.build());
              onChanged();
            } else {
              propsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public Builder addAllProps(
              Iterable<? extends Pair> values) {
            if (propsBuilder_ == null) {
              ensurePropsIsMutable();
              com.google.protobuf.AbstractMessageLite.Builder.addAll(
                  values, props_);
              onChanged();
            } else {
              propsBuilder_.addAllMessages(values);
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public Builder clearProps() {
            if (propsBuilder_ == null) {
              props_ = java.util.Collections.emptyList();
              bitField0_ = (bitField0_ & ~0x00000020);
              onChanged();
            } else {
              propsBuilder_.clear();
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public Builder removeProps(int index) {
            if (propsBuilder_ == null) {
              ensurePropsIsMutable();
              props_.remove(index);
              onChanged();
            } else {
              propsBuilder_.remove(index);
            }
            return this;
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public Pair.Builder getPropsBuilder(
              int index) {
            return getPropsFieldBuilder().getBuilder(index);
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public PairOrBuilder getPropsOrBuilder(
              int index) {
            if (propsBuilder_ == null) {
              return props_.get(index);  } else {
              return propsBuilder_.getMessageOrBuilder(index);
            }
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public java.util.List<? extends PairOrBuilder>
               getPropsOrBuilderList() {
            if (propsBuilder_ != null) {
              return propsBuilder_.getMessageOrBuilderList();
            } else {
              return java.util.Collections.unmodifiableList(props_);
            }
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public Pair.Builder addPropsBuilder() {
            return getPropsFieldBuilder().addBuilder(
                Pair.getDefaultInstance());
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public Pair.Builder addPropsBuilder(
              int index) {
            return getPropsFieldBuilder().addBuilder(
                index, Pair.getDefaultInstance());
          }
          /**
           * <code>repeated .com.alibaba.otter.canal.protocol.Pair props = 13;</code>
           *
           * <pre>
           **预留扩展*
           * </pre>
           */
          public java.util.List<Pair.Builder>
               getPropsBuilderList() {
            return getPropsFieldBuilder().getBuilderList();
          }
          private com.google.protobuf.RepeatedFieldBuilder<
              Pair, Pair.Builder, PairOrBuilder>
              getPropsFieldBuilder() {
            if (propsBuilder_ == null) {
              propsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
                  Pair, Pair.Builder, PairOrBuilder>(
                      props_,
                      ((bitField0_ & 0x00000020) == 0x00000020),
                      getParentForChildren(),
                      isClean());
              props_ = null;
            }
            return propsBuilder_;
          }

          private Object ddlSchemaName_ = "";
          /**
           * <code>optional string ddlSchemaName = 14;</code>
           *
           * <pre>
           ** ddl/query的schemaName，会存在跨库ddl，需要保留执行ddl的当前schemaName  *
           * </pre>
           */
          public boolean hasDdlSchemaName() {
            return ((bitField0_ & 0x00000040) == 0x00000040);
          }
          /**
           * <code>optional string ddlSchemaName = 14;</code>
           *
           * <pre>
           ** ddl/query的schemaName，会存在跨库ddl，需要保留执行ddl的当前schemaName  *
           * </pre>
           */
          public String getDdlSchemaName() {
            Object ref = ddlSchemaName_;
            if (!(ref instanceof String)) {
              com.google.protobuf.ByteString bs =
                  (com.google.protobuf.ByteString) ref;
              String s = bs.toStringUtf8();
              if (bs.isValidUtf8()) {
                ddlSchemaName_ = s;
              }
              return s;
            } else {
              return (String) ref;
            }
          }
          /**
           * <code>optional string ddlSchemaName = 14;</code>
           *
           * <pre>
           ** ddl/query的schemaName，会存在跨库ddl，需要保留执行ddl的当前schemaName  *
           * </pre>
           */
          public com.google.protobuf.ByteString
              getDdlSchemaNameBytes() {
            Object ref = ddlSchemaName_;
            if (ref instanceof String) {
              com.google.protobuf.ByteString b =
                  com.google.protobuf.ByteString.copyFromUtf8(
                      (String) ref);
              ddlSchemaName_ = b;
              return b;
            } else {
              return (com.google.protobuf.ByteString) ref;
            }
          }
          /**
           * <code>optional string ddlSchemaName = 14;</code>
           *
           * <pre>
           ** ddl/query的schemaName，会存在跨库ddl，需要保留执行ddl的当前schemaName  *
           * </pre>
           */
          public Builder setDdlSchemaName(
              String value) {
            if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000040;
            ddlSchemaName_ = value;
            onChanged();
            return this;
          }
          /**
           * <code>optional string ddlSchemaName = 14;</code>
           *
           * <pre>
           ** ddl/query的schemaName，会存在跨库ddl，需要保留执行ddl的当前schemaName  *
           * </pre>
           */
          public Builder clearDdlSchemaName() {
            bitField0_ = (bitField0_ & ~0x00000040);
            ddlSchemaName_ = getDefaultInstance().getDdlSchemaName();
            onChanged();
            return this;
          }
          /**
           * <code>optional string ddlSchemaName = 14;</code>
           *
           * <pre>
           ** ddl/query的schemaName，会存在跨库ddl，需要保留执行ddl的当前schemaName  *
           * </pre>
           */
          public Builder setDdlSchemaNameBytes(
              com.google.protobuf.ByteString value) {
            if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000040;
            ddlSchemaName_ = value;
            onChanged();
            return this;
          }

          // @@protoc_insertion_point(builder_scope:com.alibaba.otter.canal.protocol.RowChange)
        }

}
