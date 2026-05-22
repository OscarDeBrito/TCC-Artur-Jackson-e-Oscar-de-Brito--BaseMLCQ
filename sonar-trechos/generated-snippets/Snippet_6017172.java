public class Snippet__6017172 {

    private ModelAndView getUserApprovalPageResponse(Map<String, Object> model,
                                                         AuthorizationRequest authorizationRequest, Authentication principal) {
            logger.debug("Loading user approval page: " + userApprovalPage);
            model.putAll(userApprovalHandler.getUserApprovalRequest(authorizationRequest, principal));
            return new ModelAndView(userApprovalPage, model);
        }

}
