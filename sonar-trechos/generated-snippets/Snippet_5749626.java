public class Snippet__5749626 {

    private int calculateFanout(int remainInputChunks, int inputNum, int outputNum, int startIdx) {
        int computedFanout = Math.min(remainInputChunks, defaultFanout);

        // Why should we detect an opportunity for unbalanced merge?
        //
        // Assume that a fanout is given by 8 and there are 10 chunks.
        // If we firstly merge 3 chunks into one chunk, there remain only 8 chunks.
        // Then, we can just finish the merge phase even though we don't complete merge phase on all chunks.
        if (checkIfCanBeUnbalancedMerged(inputNum - (startIdx + computedFanout), outputNum + 1)) {
          int candidateFanout = computedFanout;
          while (checkIfCanBeUnbalancedMerged(inputNum - (startIdx + candidateFanout), outputNum + 1)) {
            candidateFanout--;
          }
          int beforeFanout = computedFanout;
          if (computedFanout > candidateFanout + 1) {
            computedFanout = candidateFanout + 1;
            info(LOG, "Fanout reduced for unbalanced merge: " + beforeFanout + " -> " + computedFanout);
          }
        }

        return computedFanout;
      }

}
