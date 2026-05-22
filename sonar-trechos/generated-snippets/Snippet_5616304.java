public class Snippet__5616304 {

    public void destroy() {
            if (cacheManagerImplicitlyCreated) {
                try {
                    net.sf.ehcache.CacheManager cacheMgr = getCacheManager();
                    cacheMgr.shutdown();
                } catch (Throwable t) {
                    if (log.isWarnEnabled()) {
                        log.warn("Unable to cleanly shutdown implicitly created CacheManager instance.  " +
                                "Ignoring (shutting down)...", t);
                    }
                } finally {
                    this.manager = null;
                    this.cacheManagerImplicitlyCreated = false;
                }
            }
        }

}
