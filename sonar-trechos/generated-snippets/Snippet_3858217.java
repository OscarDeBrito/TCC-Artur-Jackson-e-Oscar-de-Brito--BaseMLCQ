public class Snippet__3858217 {

    @DELETE
      @Path("{requestScheduleId}")
      @Produces(MediaType.TEXT_PLAIN)
      @ApiOperation(value = "Delete a request schedule", notes = "Changes status from COMPLETED to DISABLED")
      @ApiResponses({
              @ApiResponse(code = HttpStatus.SC_OK, message = MSG_SUCCESSFUL_OPERATION),
              @ApiResponse(code = HttpStatus.SC_NOT_FOUND, message = MSG_CLUSTER_OR_HOST_NOT_FOUND),
              @ApiResponse(code = HttpStatus.SC_UNAUTHORIZED, message = MSG_NOT_AUTHENTICATED),
              @ApiResponse(code = HttpStatus.SC_FORBIDDEN, message = MSG_PERMISSION_DENIED),
              @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = MSG_SERVER_ERROR),
      })
      public Response deleteRequestSchedule(@Context HttpHeaders headers,
                                            @Context UriInfo ui,
                                            @PathParam("requestScheduleId") String requestScheduleId) {
        return handleRequest(headers, null, ui, Request.Type.DELETE,
          createRequestSchedule(m_clusterName, requestScheduleId));
      }

}
