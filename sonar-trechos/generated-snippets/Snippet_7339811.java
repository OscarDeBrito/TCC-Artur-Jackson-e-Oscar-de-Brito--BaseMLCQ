public class _BuildWebServiceSoap_QueryBuildDefinitionsByUriResponse
    implements ElementDeserializable
{
    // No attributes    

    // Elements
    protected _BuildDefinitionQueryResult queryBuildDefinitionsByUriResult;

    public _BuildWebServiceSoap_QueryBuildDefinitionsByUriResponse()
    {
        super();
    }

    public _BuildWebServiceSoap_QueryBuildDefinitionsByUriResponse(
        final _BuildDefinitionQueryResult queryBuildDefinitionsByUriResult)
    {
        // TODO : Call super() instead of setting all fields directly?
        setQueryBuildDefinitionsByUriResult(queryBuildDefinitionsByUriResult);
    }

    public _BuildDefinitionQueryResult getQueryBuildDefinitionsByUriResult()
    {
        return this.queryBuildDefinitionsByUriResult;
    }

    public void setQueryBuildDefinitionsByUriResult(_BuildDefinitionQueryResult value)
    {
        this.queryBuildDefinitionsByUriResult = value;
    }

    public void readFromElement(final XMLStreamReader reader)
        throws XMLStreamException
    {
        String localName;

        // This object uses no attributes

        // Elements
        int event;

        do
        {
            event = reader.next();

            if (event == XMLStreamConstants.START_ELEMENT)
            {
                localName = reader.getLocalName();

                if (localName.equalsIgnoreCase("QueryBuildDefinitionsByUriResult"))
                {
                    this.queryBuildDefinitionsByUriResult = new _BuildDefinitionQueryResult();
                    this.queryBuildDefinitionsByUriResult.readFromElement(reader);
                }
                else
                {
                    // Read the unknown child element until its end
                    XMLStreamReaderHelper.readUntilElementEnd(reader);
                }
            }
        }
        while (event != XMLStreamConstants.END_ELEMENT);
    }
}
