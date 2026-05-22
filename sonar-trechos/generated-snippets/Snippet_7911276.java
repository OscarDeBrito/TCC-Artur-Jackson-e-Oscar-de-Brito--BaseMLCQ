@Value.Immutable
@BuckStyleImmutable
abstract class AbstractDirectoryCleanerArgs {

  @Value.Parameter
  public abstract DirectoryCleaner.PathSelector getPathSelector();

  @Value.Default
  public long getMaxTotalSizeBytes() {
    return Long.MAX_VALUE;
  }

  @Value.Default
  public int getMaxPathCount() {
    return Integer.MAX_VALUE;
  }

  @Value.Default
  public int getMinAmountOfEntriesToKeep() {
    return 0;
  }

  public abstract Optional<Long> getMaxBytesAfterDeletion();
}
