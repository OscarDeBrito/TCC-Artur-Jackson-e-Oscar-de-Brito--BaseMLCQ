public class _ClientService4Soap_GetMetadataEx2
    implements ElementSerializable
{
    // No attributes    

    // Elements
    protected _MetadataTableHaveEntry[] metadataHave;
    protected boolean useMaster;

    public _ClientService4Soap_GetMetadataEx2()
    {
        super();
    }

    public _ClientService4Soap_GetMetadataEx2(
        final _MetadataTableHaveEntry[] metadataHave,
        final boolean useMaster)
    {
        // TODO : Call super() instead of setting all fields directly?
        setMetadataHave(metadataHave);
        setUseMaster(useMaster);
    }

    public _MetadataTableHaveEntry[] getMetadataHave()
    {
        return this.metadataHave;
    }

    public void setMetadataHave(_MetadataTableHaveEntry[] value)
    {
        this.metadataHave = value;
    }

    public boolean isUseMaster()
    {
        return this.useMaster;
    }

    public void setUseMaster(boolean value)
    {
        this.useMaster = value;
    }

    public void writeAsElement(
        final XMLStreamWriter writer,
        final String name)
        throws XMLStreamException
    {
        writer.writeStartElement(name);

        // Elements
        if (this.metadataHave != null)
        {
            /*
             * The element type is an array.
             */
            writer.writeStartElement("metadataHave");

            for (int iterator0 = 0; iterator0 < this.metadataHave.length; iterator0++)
            {
                this.metadataHave[iterator0].writeAsElement(
                    writer,
                    "MetadataTableHaveEntry");
            }

            writer.writeEndElement();
        }

        XMLStreamWriterHelper.writeElement(
            writer,
            "useMaster",
            this.useMaster);

        writer.writeEndElement();
    }
}
