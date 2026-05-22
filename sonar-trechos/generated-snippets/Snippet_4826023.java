public class Snippet__4826023 {

    private Persistability determinePersistability() {
            final NotPersistableFacet notPersistableFacet = getFacet(NotPersistableFacet.class);
            if (notPersistableFacet == null) {
                return Persistability.USER_PERSISTABLE;
            }
            final NotPersistable.By initiatedBy = notPersistableFacet.value();
            if (initiatedBy == NotPersistable.By.USER_OR_PROGRAM) {
                return Persistability.TRANSIENT;
            } else if (initiatedBy == NotPersistable.By.USER) {
                return Persistability.PROGRAM_PERSISTABLE;
            } else {
                return Persistability.USER_PERSISTABLE;
            }
        }

}
