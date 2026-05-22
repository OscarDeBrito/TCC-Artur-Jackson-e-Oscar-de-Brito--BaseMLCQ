public class Snippet__7810086 {

    @Override
      protected void setLastModifiedAndAccessedTimes(final long lastModified, final long lastAccessed) {
        _setLastModified(lastModified);
        if (!DISABLE_ACCESS_TIME_UPDATE_ON_PUT) {
          setLastAccessed(lastAccessed);
        }
      }

}
