public class Snippet__8402603 {

    private void tryConfigureBulkRequests() throws JSchException {
            Integer bulkRequests = endpoint.getConfiguration().getBulkRequests();

            if (bulkRequests != null) {
                LOG.trace("configuring channel to use up to {} bulk request(s)", bulkRequests);

                channel.setBulkRequests(bulkRequests);
            }
        }

}
