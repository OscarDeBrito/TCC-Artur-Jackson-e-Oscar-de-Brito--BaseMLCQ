public class Snippet__8543153 {

    @Override
        public void execute() {
            CallContext.current().setEventDetails("Virtual router element Id: " + getEntityId());
            VirtualRouterProvider result = _service.get(0).getInternalLoadBalancerElement(getEntityId());
            if (result != null) {
                InternalLoadBalancerElementResponse response = _responseGenerator.createInternalLbElementResponse(result);
                response.setResponseName(getCommandName());
                this.setResponseObject(response);
            } else {
                throw new ServerApiException(ApiErrorCode.INTERNAL_ERROR, "Failed to add Virtual Router entity to physical network");
            }
        }

}
