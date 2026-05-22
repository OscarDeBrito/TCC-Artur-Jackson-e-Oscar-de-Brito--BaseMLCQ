public class Snippet__4297095 {

    @Override
        public List<Permission> sessionPermissions(Session session)
            throws SecurityException
        {
            VUtil.assertNotNull(session, GlobalErrIds.USER_SESS_NULL, CLS_NM + ".sessionPermissions");
            List<Permission> retPerms;
            FortRequest request = new FortRequest();
            request.setContextId(this.contextId);
            request.setSession(session);
            String szRequest = RestUtils.marshal(request);
            String szResponse = RestUtils.getInstance().post(szRequest, HttpIds.ADMIN_PERMS);
            FortResponse response = RestUtils.unmarshall(szResponse);
            if (response.getErrorCode() == 0)
            {
                retPerms = response.getEntities();
                Session outSession = response.getSession();
                session.copy(outSession);
            }
            else
            {
                throw new SecurityException(response.getErrorCode(), response.getErrorMessage());
            }
            return retPerms;
        }

}
