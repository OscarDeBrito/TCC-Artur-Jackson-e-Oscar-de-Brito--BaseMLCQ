public class Snippet__8934890 {

    @Override
        public AggregationBuffer getNewAggregationBuffer() throws HiveException {
          NGramAggBuf result = new NGramAggBuf();
          result.nge = new NGramEstimator();
          reset(result);
          return result;
        }

}
