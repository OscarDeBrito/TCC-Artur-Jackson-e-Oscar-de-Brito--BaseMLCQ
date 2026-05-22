public class Snippet__8971449 {

    void migrateGlobal(int counts[], long segCounts[], int subIndex, OrdinalMap map) {
    
        final LongValues ordMap = map.getGlobalOrds(subIndex);
        // missing count
        counts[0] += (int) (segCounts[0]>>32);
    
        // migrate actual ordinals
        for (int ord = 1; ord <= segmentSSDV.getValueCount(); ord++) {
          int count = (int) (segCounts[ord]>>32);
          if (count != 0) {
            counts[1+(int) ordMap.get(ord-1)] += count;
          }
        }
      }

}
