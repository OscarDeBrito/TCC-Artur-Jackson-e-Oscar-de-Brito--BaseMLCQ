public class Snippet__4864235 {

    private static final class RequestCounter extends CacheStatsMBeanCounter {

            RequestCounter(CacheStatsMBean stats) {
                super(stats, REQUEST);
            }

            @Override
            public long getCount() {
                return stats.getRequestCount();
            }
        }

}
