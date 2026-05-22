public class BatchCreateRequest<T extends RecordTemplate> extends Request<CollectionResponse<CreateStatus>>
{
  BatchCreateRequest(Map<String, String> headers,
                     List<HttpCookie> cookies,
                     BatchCreateDecoder<?> decoder,
                     CollectionRequest<T> input,
                     ResourceSpec resourceSpec,
                     Map<String, Object> queryParams,
                     Map<String, Class<?>> queryParamClasses,
                     String baseUriTemplate,
                     Map<String, Object> pathKeys,
                     RestliRequestOptions requestOptions,
                     List<Object> streamingAttachments)
  {
    super(ResourceMethod.BATCH_CREATE,
          input,
          headers,
          cookies,
          decoder,
          resourceSpec,
          queryParams,
          queryParamClasses,
          null,
          baseUriTemplate,
          pathKeys,
          requestOptions,
          streamingAttachments);
  }
}
