private static class ToValueFunction implements Function<Tuple2<String, Iterable<Tuple>>, Tuple> {
        @Override
        public Tuple call(Tuple2<String, Iterable<Tuple>> next) throws Exception {
            Tuple res = tf.newTuple();
            res.append(next._1());
            Iterator<Tuple> iter = next._2().iterator();
            DataBag bag = bf.newDefaultBag();
            while(iter.hasNext()) {
                bag.add(iter.next());
            }
            res.append(bag);
            LOG.info("ToValueFunction1 out:" + res);
            return res;
        }
    }
