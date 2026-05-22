public class Snippet__5618836 {

    public Collection fromRealm(String realmName) {
            if (realmPrincipals == null || realmPrincipals.isEmpty()) {
                return Collections.EMPTY_SET;
            }
            Set principals = realmPrincipals.get(realmName);
            if (principals == null || principals.isEmpty()) {
                principals = Collections.EMPTY_SET;
            }
            return Collections.unmodifiableSet(principals);
        }

}
