public class Snippet__8246323 {

    private void setResponse(CachedSourceResponse response) throws IOException {
            this.response = response;
            if (this.expires != 0) {
                try {
                    this.cache.store(this.cacheKey, this.response);
                } catch (ProcessingException e) {
                    throw new CascadingIOException("Failure storing response.", e);
                }
            }
        }

}
