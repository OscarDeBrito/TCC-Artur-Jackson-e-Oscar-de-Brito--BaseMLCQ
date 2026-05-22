public class Snippet__7074998 {

    private <T> Task<Response<T>> createTask(final String name, final Request<T> request,
          final RequestContext requestContext, RequestConfig config) {
        LOGGER.debug("createTask, name: '{}', config: {}", name, config);
        if (_d2RequestTimeoutEnabled) {
          return createTaskWithD2Timeout(name, request, requestContext, config);
        } else {
          return createTaskWithTimeout(name, request, requestContext, config);
        }
      }

}
