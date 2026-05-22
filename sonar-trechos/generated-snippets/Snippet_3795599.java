public static class DefaultExpiredCallback<K> implements ExpiredCallback<K> {
        protected static final Logger LOG = LoggerFactory.getLogger(TimeCacheQueue.DefaultExpiredCallback.class);

        protected String queueName;

        public DefaultExpiredCallback(String queueName) {
            this.queueName = queueName;
        }

        public void expire(K entry) {
            LOG.info("TimeCacheQueue " + queueName + " entry:" + entry + ", timeout");
        }
    }
