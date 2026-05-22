public class Snippet__8601907 {

    void updateCachedLocationOnError(HRegionLocation loc, Throwable exception) {
        AsyncRegionLocatorHelper.updateCachedLocationOnError(loc, exception, this::getCachedLocation,
          this::addLocationToCache, this::removeLocationFromCache);
      }

}
