@SdkInternalApi
public final class AwsQueryResponseHandler<T extends AwsResponse> implements HttpResponseHandler<T> {

    private static final Logger log = Logger.loggerFor(AwsQueryResponseHandler.class);

    private final QueryProtocolUnmarshaller unmarshaller;
    private final Function<SdkHttpFullResponse, SdkPojo> pojoSupplier;


    public AwsQueryResponseHandler(QueryProtocolUnmarshaller unmarshaller,
                                   Function<SdkHttpFullResponse, SdkPojo> pojoSupplier) {
        this.unmarshaller = unmarshaller;
        this.pojoSupplier = pojoSupplier;
    }

    @Override
    public T handle(SdkHttpFullResponse response, ExecutionAttributes executionAttributes) throws Exception {
        try {
            return unmarshallResponse(response);
        } finally {
            response.content().ifPresent(i -> {
                try {
                    i.close();
                } catch (IOException e) {
                    log.warn(() -> "Error closing HTTP content.", e);
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    private T unmarshallResponse(SdkHttpFullResponse response) throws Exception {
        SdkStandardLogger.REQUEST_LOGGER.trace(() -> "Parsing service response XML.");
        Pair<T, Map<String, String>> result = unmarshaller.unmarshall(pojoSupplier.apply(response), response);
        SdkStandardLogger.REQUEST_LOGGER.trace(() -> "Done parsing service response.");
        AwsResponseMetadata responseMetadata = generateResponseMetadata(response, result.right());
        return (T) result.left().toBuilder().responseMetadata(responseMetadata).build();
    }

    /**
     * Create the default {@link AwsResponseMetadata}. This might be wrapped by a service
     * specific metadata object to provide modeled access to additional metadata. (See S3 and Kinesis).
     */
    private AwsResponseMetadata generateResponseMetadata(SdkHttpResponse response, Map<String, String> metadata) {
        if (!metadata.containsKey(AWS_REQUEST_ID)) {
            metadata.put(AWS_REQUEST_ID,
                         response.firstMatchingHeader(X_AMZN_REQUEST_ID_HEADER).orElse(null));
        }

        response.headers().forEach((key, value) -> metadata.put(key, value.get(0)));
        return DefaultAwsResponseMetadata.create(metadata);
    }

    @Override
    public boolean needsConnectionLeftOpen() {
        // Query doesn't support streaming so this is always false
        return false;
    }

}
