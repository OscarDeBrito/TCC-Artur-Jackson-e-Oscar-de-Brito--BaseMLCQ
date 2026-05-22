public interface WorkingRange {

  /**
   * Return true to trigger{@literal @}OnEnteredRange method defined in the component, do nothing
   * otherwise.
   */
  boolean shouldEnterRange(
      int position,
      int firstVisibleIndex,
      int lastVisibleIndex,
      int firstFullyVisibleIndex,
      int lastFullyVisibleIndex);

  /**
   * Return true to trigger{@literal @}OnExitedRange method defined in the component, do nothing
   * otherwise.
   */
  boolean shouldExitRange(
      int position,
      int firstVisibleIndex,
      int lastVisibleIndex,
      int firstFullyVisibleIndex,
      int lastFullyVisibleIndex);
}
