public class Snippet__4301441 {

    public static boolean pricipalCompareIgnoreRealm(PrincipalName princ1, PrincipalName princ2)
                throws KrbException {

            if (princ1 != null && princ2 != null) {
                princ1.setRealm(null);
                princ2.setRealm(null);
                if (princ1.getName().equals(princ2.getName())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new KrbException("principal can't be null.");
            }
        }

}
