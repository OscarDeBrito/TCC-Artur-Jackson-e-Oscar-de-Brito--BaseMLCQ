public class Snippet__5759451 {

    @VisibleForTesting
      public static Optional<Tablespace> removeTablespaceForTest(String name) {
        Tablespace existing = null;
        synchronized (SPACES_URIS_MAP) {
          URI uri = SPACES_URIS_MAP.remove(name);
          if (uri != null) {
            existing = TABLE_SPACES.remove(uri);
          }
        }
        return Optional.ofNullable(existing);
      }

}
