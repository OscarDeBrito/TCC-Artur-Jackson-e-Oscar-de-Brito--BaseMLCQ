public class Snippet__6765637 {

    @Override
        public APINodeList<Flight> execute(Map<String, Object> extraParams) throws APIException {
          ResponseWrapper rw = executeInternal(extraParams);
          lastResponse = parseResponse(rw.getBody(),rw.getHeader());
          return lastResponse;
        }

}
