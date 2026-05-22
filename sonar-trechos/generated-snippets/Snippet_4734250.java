public class Snippet__4734250 {

    public Long getTotalLogRecordsCompacted() {
        Long totalLogRecords = 0L;
        for (Map.Entry<String, List<HoodieWriteStat>> entry : partitionToWriteStats.entrySet()) {
          for (HoodieWriteStat writeStat : entry.getValue()) {
            totalLogRecords += writeStat.getTotalLogRecords();
          }
        }
        return totalLogRecords;
      }

}
