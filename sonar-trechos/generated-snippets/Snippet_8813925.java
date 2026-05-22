public class Snippet__8813925 {

    public String recv_registerComputeResource() throws org.apache.airavata.model.error.InvalidRequestException, org.apache.airavata.model.error.AiravataClientException, org.apache.airavata.model.error.AiravataSystemException, org.apache.airavata.model.error.AuthorizationException, org.apache.thrift.TException
        {
          registerComputeResource_result result = new registerComputeResource_result();
          receiveBase(result, "registerComputeResource");
          if (result.isSetSuccess()) {
            return result.success;
          }
          if (result.ire != null) {
            throw result.ire;
          }
          if (result.ace != null) {
            throw result.ace;
          }
          if (result.ase != null) {
            throw result.ase;
          }
          if (result.ae != null) {
            throw result.ae;
          }
          throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "registerComputeResource failed: unknown result");
        }

}
