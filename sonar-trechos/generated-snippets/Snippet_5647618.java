public class Snippet__5647618 {

    final void startFollowing(final List<Relation> following) throws InternalDataStoreException {
            for (int i = following.size(); --i >= 0;) {
                if (following.get(i) == this) {
                    throw new InternalDataStoreException(Errors.format(Errors.Keys.CircularReference));
                }
            }
            following.add(this);
        }

}
