public interface AllTypesOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // optional double doubleType = 1;
    /**
     * <code>optional double doubleType = 1;</code>
     */
    boolean hasDoubleType();
    /**
     * <code>optional double doubleType = 1;</code>
     */
    double getDoubleType();

    // optional float floatType = 2;
    /**
     * <code>optional float floatType = 2;</code>
     */
    boolean hasFloatType();
    /**
     * <code>optional float floatType = 2;</code>
     */
    float getFloatType();

    // optional int32 int32Type = 3;
    /**
     * <code>optional int32 int32Type = 3;</code>
     */
    boolean hasInt32Type();
    /**
     * <code>optional int32 int32Type = 3;</code>
     */
    int getInt32Type();

    // optional int64 int64Type = 4;
    /**
     * <code>optional int64 int64Type = 4;</code>
     */
    boolean hasInt64Type();
    /**
     * <code>optional int64 int64Type = 4;</code>
     */
    long getInt64Type();

    // optional uint32 uint32Type = 5;
    /**
     * <code>optional uint32 uint32Type = 5;</code>
     */
    boolean hasUint32Type();
    /**
     * <code>optional uint32 uint32Type = 5;</code>
     */
    int getUint32Type();

    // optional uint64 uint64Type = 6;
    /**
     * <code>optional uint64 uint64Type = 6;</code>
     */
    boolean hasUint64Type();
    /**
     * <code>optional uint64 uint64Type = 6;</code>
     */
    long getUint64Type();

    // optional sint32 sint32Type = 7;
    /**
     * <code>optional sint32 sint32Type = 7;</code>
     */
    boolean hasSint32Type();
    /**
     * <code>optional sint32 sint32Type = 7;</code>
     */
    int getSint32Type();

    // optional sint64 sint64Type = 8;
    /**
     * <code>optional sint64 sint64Type = 8;</code>
     */
    boolean hasSint64Type();
    /**
     * <code>optional sint64 sint64Type = 8;</code>
     */
    long getSint64Type();

    // optional fixed32 fixed32Type = 9;
    /**
     * <code>optional fixed32 fixed32Type = 9;</code>
     */
    boolean hasFixed32Type();
    /**
     * <code>optional fixed32 fixed32Type = 9;</code>
     */
    int getFixed32Type();

    // optional fixed64 fixed64Type = 10;
    /**
     * <code>optional fixed64 fixed64Type = 10;</code>
     */
    boolean hasFixed64Type();
    /**
     * <code>optional fixed64 fixed64Type = 10;</code>
     */
    long getFixed64Type();

    // optional sfixed32 sfixed32Type = 11;
    /**
     * <code>optional sfixed32 sfixed32Type = 11;</code>
     */
    boolean hasSfixed32Type();
    /**
     * <code>optional sfixed32 sfixed32Type = 11;</code>
     */
    int getSfixed32Type();

    // optional sfixed64 sfixed64Type = 12;
    /**
     * <code>optional sfixed64 sfixed64Type = 12;</code>
     */
    boolean hasSfixed64Type();
    /**
     * <code>optional sfixed64 sfixed64Type = 12;</code>
     */
    long getSfixed64Type();

    // optional bool boolType = 13;
    /**
     * <code>optional bool boolType = 13;</code>
     */
    boolean hasBoolType();
    /**
     * <code>optional bool boolType = 13;</code>
     */
    boolean getBoolType();

    // optional string stringType = 14;
    /**
     * <code>optional string stringType = 14;</code>
     */
    boolean hasStringType();
    /**
     * <code>optional string stringType = 14;</code>
     */
    java.lang.String getStringType();
    /**
     * <code>optional string stringType = 14;</code>
     */
    com.google.protobuf.ByteString
        getStringTypeBytes();

    // optional bytes bytesType = 15;
    /**
     * <code>optional bytes bytesType = 15;</code>
     */
    boolean hasBytesType();
    /**
     * <code>optional bytes bytesType = 15;</code>
     */
    com.google.protobuf.ByteString getBytesType();

    // repeated .MapFieldEntry mapType = 16;
    /**
     * <code>repeated .MapFieldEntry mapType = 16;</code>
     */
    java.util.List<org.apache.hadoop.hive.contrib.serde2.SampleProtos.MapFieldEntry> 
        getMapTypeList();
    /**
     * <code>repeated .MapFieldEntry mapType = 16;</code>
     */
    org.apache.hadoop.hive.contrib.serde2.SampleProtos.MapFieldEntry getMapType(int index);
    /**
     * <code>repeated .MapFieldEntry mapType = 16;</code>
     */
    int getMapTypeCount();
    /**
     * <code>repeated .MapFieldEntry mapType = 16;</code>
     */
    java.util.List<? extends org.apache.hadoop.hive.contrib.serde2.SampleProtos.MapFieldEntryOrBuilder> 
        getMapTypeOrBuilderList();
    /**
     * <code>repeated .MapFieldEntry mapType = 16;</code>
     */
    org.apache.hadoop.hive.contrib.serde2.SampleProtos.MapFieldEntryOrBuilder getMapTypeOrBuilder(
        int index);

    // repeated string stringListType = 17;
    /**
     * <code>repeated string stringListType = 17;</code>
     */
    java.util.List<java.lang.String>
    getStringListTypeList();
    /**
     * <code>repeated string stringListType = 17;</code>
     */
    int getStringListTypeCount();
    /**
     * <code>repeated string stringListType = 17;</code>
     */
    java.lang.String getStringListType(int index);
    /**
     * <code>repeated string stringListType = 17;</code>
     */
    com.google.protobuf.ByteString
        getStringListTypeBytes(int index);

    // optional .Mesg1 messageType = 18;
    /**
     * <code>optional .Mesg1 messageType = 18;</code>
     */
    boolean hasMessageType();
    /**
     * <code>optional .Mesg1 messageType = 18;</code>
     */
    org.apache.hadoop.hive.contrib.serde2.SampleProtos.Mesg1 getMessageType();
    /**
     * <code>optional .Mesg1 messageType = 18;</code>
     */
    org.apache.hadoop.hive.contrib.serde2.SampleProtos.Mesg1OrBuilder getMessageTypeOrBuilder();

    // repeated .Mesg1 messageListType = 19;
    /**
     * <code>repeated .Mesg1 messageListType = 19;</code>
     */
    java.util.List<org.apache.hadoop.hive.contrib.serde2.SampleProtos.Mesg1> 
        getMessageListTypeList();
    /**
     * <code>repeated .Mesg1 messageListType = 19;</code>
     */
    org.apache.hadoop.hive.contrib.serde2.SampleProtos.Mesg1 getMessageListType(int index);
    /**
     * <code>repeated .Mesg1 messageListType = 19;</code>
     */
    int getMessageListTypeCount();
    /**
     * <code>repeated .Mesg1 messageListType = 19;</code>
     */
    java.util.List<? extends org.apache.hadoop.hive.contrib.serde2.SampleProtos.Mesg1OrBuilder> 
        getMessageListTypeOrBuilderList();
    /**
     * <code>repeated .Mesg1 messageListType = 19;</code>
     */
    org.apache.hadoop.hive.contrib.serde2.SampleProtos.Mesg1OrBuilder getMessageListTypeOrBuilder(
        int index);

    // optional .AllTypes.Enum1 enumType = 20;
    /**
     * <code>optional .AllTypes.Enum1 enumType = 20;</code>
     */
    boolean hasEnumType();
    /**
     * <code>optional .AllTypes.Enum1 enumType = 20;</code>
     */
    org.apache.hadoop.hive.contrib.serde2.SampleProtos.AllTypes.Enum1 getEnumType();
  }
