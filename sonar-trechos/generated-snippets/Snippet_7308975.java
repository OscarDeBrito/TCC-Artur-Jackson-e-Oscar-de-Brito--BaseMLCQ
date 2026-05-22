public class _AuthorizationServiceSoap_GetObjectClass
    implements ElementSerializable
{
    // No attributes    

    // Elements
    protected String objectId;

    public _AuthorizationServiceSoap_GetObjectClass()
    {
        super();
    }

    public _AuthorizationServiceSoap_GetObjectClass(final String objectId)
    {
        // TODO : Call super() instead of setting all fields directly?
        setObjectId(objectId);
    }

    public String getObjectId()
    {
        return this.objectId;
    }

    public void setObjectId(String value)
    {
        this.objectId = value;
    }

    public void writeAsElement(
        final XMLStreamWriter writer,
        final String name)
        throws XMLStreamException
    {
        writer.writeStartElement(name);

        // Elements
        XMLStreamWriterHelper.writeElement(
            writer,
            "objectId",
            this.objectId);

        writer.writeEndElement();
    }
}
