public class Snippet__9481601 {

    @Override
      public GetCompInstancesResponseProto getCompInstances(
          GetCompInstancesRequestProto request) throws IOException, YarnException {
        try {
          return proxy.getCompInstances(null, request);
        } catch (ServiceException e) {
          RPCUtil.unwrapAndThrowException(e);
        }
        return null;
      }

}
