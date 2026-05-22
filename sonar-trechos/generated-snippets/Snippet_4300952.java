public class Snippet__4300952 {

    @Override
        public KrbIdentity getIdentity(String principalName) throws KrbException {
            if (idCache.containsKey(principalName)) {
                return idCache.get(principalName);
            }

            KrbIdentity identity = underlying.getIdentity(principalName);
            if (identity != null) {
                idCache.put(principalName, identity);
            }

            return identity;
        }

}
