public static class ExclamationBolt extends BaseRichBolt {
        //Have a crummy cache to show off shared memory accounting
        private static final ConcurrentHashMap<String, String> myCrummyCache =
            new ConcurrentHashMap<>();
        private static final int CACHE_SIZE = 100_000;
        OutputCollector _collector;

        protected static String getFromCache(String key) {
            return myCrummyCache.get(key);
        }

        protected static void addToCache(String key, String value) {
            myCrummyCache.putIfAbsent(key, value);
            int numToRemove = myCrummyCache.size() - CACHE_SIZE;
            if (numToRemove > 0) {
                //Remove something randomly...
                Iterator<Entry<String, String>> it = myCrummyCache.entrySet().iterator();
                for (; numToRemove > 0 && it.hasNext(); numToRemove--) {
                    it.next();
                    it.remove();
                }
            }
        }

        @Override
        public void prepare(Map<String, Object> conf, TopologyContext context, OutputCollector collector) {
            _collector = collector;
        }

        @Override
        public void execute(Tuple tuple) {
            String orig = tuple.getString(0);
            String ret = getFromCache(orig);
            if (ret == null) {
                ret = orig + "!!!";
                addToCache(orig, ret);
            }
            _collector.emit(tuple, new Values(ret));
            _collector.ack(tuple);
        }

        @Override
        public void declareOutputFields(OutputFieldsDeclarer declarer) {
            declarer.declare(new Fields("word"));
        }
    }
