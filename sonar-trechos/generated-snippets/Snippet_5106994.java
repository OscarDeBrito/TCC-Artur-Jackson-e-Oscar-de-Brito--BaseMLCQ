public class Snippet__5106994 {

    private int computeBatchSize(int position, int length) {
            int batchSize = QUERY_BATCH_SIZE;
            while (length - position < batchSize) {
                batchSize = batchSize >> 1;
            }
            return batchSize;
        }

}
