public abstract class AbstractRecordsInspector {

  private int processedRecordCount;

  /**
   * Checks if current number of processed records does not exceed max batch size.
   *
   * @return true if reached max number of records in batch
   */
  public boolean isBatchFull() {
    return processedRecordCount >= HiveAbstractReader.TARGET_RECORD_COUNT;
  }

  /**
   * @return number of processed records
   */
  public int getProcessedRecordCount() {
    return processedRecordCount;
  }

  /**
   * Increments current number of processed records.
   */
  public void incrementProcessedRecordCount() {
    processedRecordCount++;
  }

  /**
   * When batch of data was sent, number of processed records should be reset.
   */
  public void reset() {
    processedRecordCount = 0;
  }

  /**
   * Returns value holder where next value will be written.
   *
   * @return value holder
   */
  public abstract Object getValueHolder();

  /**
   * @return value holder with written value
   */
  public abstract Object getNextValue();

}
