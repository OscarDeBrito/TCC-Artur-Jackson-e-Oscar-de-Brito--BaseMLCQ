public class Snippet__8953503 {

    private static SelectOperator genReduceSinkAndBacktrackSelect(Operator<?> input,
              ExprNodeDesc[] keys, int tag, ArrayList<ExprNodeDesc> partitionCols, String order,
              String nullOrder, int numReducers, Operation acidOperation, HiveConf hiveConf)
                  throws SemanticException {
        return genReduceSinkAndBacktrackSelect(input, keys, tag, partitionCols, order, nullOrder,
            numReducers, acidOperation, hiveConf, input.getSchema().getColumnNames());
      }

}
