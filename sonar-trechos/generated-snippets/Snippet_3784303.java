public class Snippet__3784303 {

    @Override
            public void execute(Tuple tuple, Object state, TimeWindow window) {
                LOG.info("executing on window:{}", window);
                Map<String, Integer> counts = (Map<String, Integer>) state;
                List<Object> partialWordCounts = tuple.getValues();
                for (Object partialWordCount : partialWordCounts) {
                    Pair<String, Integer> pair = (Pair<String, Integer>) partialWordCount;
                    counts.put(pair.getFirst(), pair.getSecond());
                }
            }

}
