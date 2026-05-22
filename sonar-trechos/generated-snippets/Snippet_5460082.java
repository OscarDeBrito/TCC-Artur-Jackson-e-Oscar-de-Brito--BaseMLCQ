public class Snippet__5460082 {

    @POST
        @Path("/{tenant}/{namespace}/subscribeRate")
        @ApiOperation(value = "Set subscribe-rate throttling for all topics of the namespace")
        @ApiResponses(value = { @ApiResponse(code = 403, message = "Don't have admin permission") })
        public void setSubscribeRate(@PathParam("tenant") String tenant, @PathParam("namespace") String namespace,
                                    SubscribeRate subscribeRate) {
            validateNamespaceName(tenant, namespace);
            internalSetSubscribeRate(subscribeRate);
        }

}
