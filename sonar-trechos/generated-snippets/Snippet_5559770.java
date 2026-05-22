public class Snippet__5559770 {

    private boolean isPrivilegeForResource(TSentryPrivilege tSentryPrivilege, Resource resource) {
        final java.util.Iterator<TAuthorizable> authorizablesIterator = tSentryPrivilege.getAuthorizablesIterator();
        while (authorizablesIterator.hasNext()) {
          TAuthorizable tAuthorizable = authorizablesIterator.next();
          if (tAuthorizable.getType().equals(resource.resourceType().name())) {
            return true;
          }
        }
        return false;
      }

}
