public class Snippet__4435287 {

    @POST
        @Path("resume/{type}/{entity}")
        @Produces(MediaType.APPLICATION_JSON)
        @Monitored(event = "resume-instance")
        @Override
        public InstancesResult resumeInstance(
                @Context HttpServletRequest request,
                @Dimension("type") @PathParam("type") String type,
                @Dimension("entity") @PathParam("entity") String entity,
                @Dimension("start-time") @QueryParam("start") String startStr,
                @Dimension("end-time") @QueryParam("end") String endStr,
                @Dimension("colo") @QueryParam("colo") String colo,
                @Dimension("lifecycle") @QueryParam("lifecycle") List<LifeCycle> lifeCycles) {
            try {
                return super.resumeInstance(request, type, entity, startStr, endStr, colo, lifeCycles);
            } catch (Throwable throwable) {
                throw FalconWebException.newAPIException(throwable);
            }
        }

}
