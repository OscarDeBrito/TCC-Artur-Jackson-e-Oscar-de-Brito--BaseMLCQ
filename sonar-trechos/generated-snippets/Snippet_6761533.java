public class Snippet__6761533 {

    @Override
        public APINode execute(Map<String, Object> extraParams) throws APIException {
          ResponseWrapper rw = executeInternal(extraParams);
          lastResponse = parseResponse(rw.getBody(), rw.getHeader());
          return lastResponse;
        }

}
