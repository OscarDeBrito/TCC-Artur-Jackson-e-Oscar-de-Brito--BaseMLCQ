public class EventFilter extends MonitoringFilter {

    public static final NodeId TypeId = Identifiers.EventFilter;
    public static final NodeId BinaryEncodingId = Identifiers.EventFilter_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EventFilter_Encoding_DefaultXml;

    protected final SimpleAttributeOperand[] selectClauses;
    protected final ContentFilter whereClause;

    public EventFilter() {
        super();
        this.selectClauses = null;
        this.whereClause = null;
    }

    public EventFilter(SimpleAttributeOperand[] selectClauses, ContentFilter whereClause) {
        super();
        this.selectClauses = selectClauses;
        this.whereClause = whereClause;
    }

    @Nullable
    public SimpleAttributeOperand[] getSelectClauses() { return selectClauses; }

    public ContentFilter getWhereClause() { return whereClause; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SelectClauses", selectClauses)
            .add("WhereClause", whereClause)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<EventFilter> {

        @Override
        public Class<EventFilter> getType() {
            return EventFilter.class;
        }

        @Override
        public EventFilter decode(UaDecoder decoder) throws UaSerializationException {
            SimpleAttributeOperand[] selectClauses =
                decoder.readBuiltinStructArray(
                    "SelectClauses",
                    SimpleAttributeOperand.class
                );
            ContentFilter whereClause = (ContentFilter) decoder.readBuiltinStruct("WhereClause", ContentFilter.class);

            return new EventFilter(selectClauses, whereClause);
        }

        @Override
        public void encode(EventFilter value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStructArray(
                "SelectClauses",
                value.selectClauses,
                SimpleAttributeOperand.class
            );
            encoder.writeBuiltinStruct("WhereClause", value.whereClause, ContentFilter.class);
        }
    }

}
