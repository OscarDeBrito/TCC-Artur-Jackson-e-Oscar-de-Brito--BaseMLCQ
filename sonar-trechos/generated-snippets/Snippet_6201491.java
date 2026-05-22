public class Snippet__6201491 {

    @Override
      protected SystemPermissionsImpl getEntity(String userId, String instanceId)
          throws NotFoundException, ServerException {
        try {
          final List<SystemPermissionsImpl> existent = doGetByUser(userId);
          if (existent.isEmpty()) {
            throw new NotFoundException(format("System permissions for user '%s' not found", userId));
          }
          return existent.get(0);
        } catch (RuntimeException e) {
          throw new ServerException(e.getMessage(), e);
        }
      }

}
