public class Snippet__7337150 {

    public _Job[] listJobs()
            throws TransportException, SOAPFault
        {
            final _ReportingService2005Soap_ListJobs requestData = new _ReportingService2005Soap_ListJobs();

            final SOAPRequest request = createSOAPRequest(
                    "ListJobs",
                    new SOAPMethodRequestWriter()
                    {
                        public void writeSOAPRequest(
                            final XMLStreamWriter writer,
                            final OutputStream out)
                            throws XMLStreamException, IOException
                        {
                            requestData.writeAsElement(
                                writer,
                                "ListJobs");
                        }
                    });

            final _ReportingService2005Soap_ListJobsResponse responseData = new _ReportingService2005Soap_ListJobsResponse();

            executeSOAPRequest(
                request,
                "ListJobsResponse",
                new SOAPMethodResponseReader()
                {
                    public void readSOAPResponse(
                        final XMLStreamReader reader,
                        final InputStream in)
                        throws XMLStreamException, IOException
                    {
                        responseData.readFromElement(reader);
                    }
                });

            return responseData.getJobs();
        }

}
