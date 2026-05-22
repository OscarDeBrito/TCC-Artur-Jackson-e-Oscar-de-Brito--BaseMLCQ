public class Snippet__6329944 {

    @Override
        public void delete(KapuaId scopeId, KapuaId groupId) throws KapuaException {
            //
            // Argument validation
            ArgumentValidator.notNull(scopeId, "scopeId");
            ArgumentValidator.notNull(groupId, "groupId");

            //
            // Check Access
            AUTHORIZATION_SERVICE.checkPermission(PERMISSION_FACTORY.newPermission(AuthorizationDomains.GROUP_DOMAIN, Actions.delete, scopeId));

            //
            // Check existence
            if (find(scopeId, groupId) == null) {
                throw new KapuaEntityNotFoundException(Group.TYPE, groupId);
            }

            //
            // Do delete
            entityManagerSession.onTransactedAction(em -> GroupDAO.delete(em, scopeId, groupId));
        }

}
