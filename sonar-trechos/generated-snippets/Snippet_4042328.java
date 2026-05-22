public class Snippet__4042328 {

    @Override
        public Mutation apply(Key key) {
          // Verify that the entity to delete has a complete key.
          checkArgument(
              isValidKey(key),
              "Keys to be deleted from the Cloud Datastore must be complete:\n%s",
              key);

          return makeDelete(key).build();
        }

}
