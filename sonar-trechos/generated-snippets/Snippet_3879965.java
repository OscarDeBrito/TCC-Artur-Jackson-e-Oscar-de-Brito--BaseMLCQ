@StaticallyInject
@Path("/logout")
public class LogoutService {

  @Inject
  private static AuditLogger auditLogger;

  @GET @ApiIgnore // until documented
  @Produces("text/plain")
  public Response performLogout(@Context HttpServletRequest servletRequest) {
    auditLog(servletRequest);
    SecurityContextHolder.clearContext();
    servletRequest.getSession().invalidate();
    return Response.status(Response.Status.OK).build();
  }

  /**
   * Creates and send and audit log event that the user has successfully logged out
   * @param servletRequest
   */
  private void auditLog(HttpServletRequest servletRequest) {
    if(!auditLogger.isEnabled()) {
      return;
    }
    LogoutAuditEvent logoutEvent = LogoutAuditEvent.builder()
      .withTimestamp(System.currentTimeMillis())
      .withRemoteIp(RequestUtils.getRemoteAddress(servletRequest))
      .withUserName(AuthorizationHelper.getAuthenticatedName())
      .withProxyUserName(AuthorizationHelper.getProxyUserName())
      .build();
    auditLogger.log(logoutEvent);
  }
}
