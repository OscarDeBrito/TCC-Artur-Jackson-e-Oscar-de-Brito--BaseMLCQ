@InterfaceAudience.Private
public class ClientSimpleScanner extends ClientScanner {
  public ClientSimpleScanner(Configuration configuration, Scan scan, TableName name,
      ClusterConnection connection, RpcRetryingCallerFactory rpcCallerFactory,
      RpcControllerFactory rpcControllerFactory, ExecutorService pool,
      int replicaCallTimeoutMicroSecondScan) throws IOException {
    super(configuration, scan, name, connection, rpcCallerFactory, rpcControllerFactory, pool,
        replicaCallTimeoutMicroSecondScan);
  }

  @Override
  protected boolean setNewStartKey() {
    if (noMoreResultsForScan(scan, currentRegion)) {
      return false;
    }
    scan.withStartRow(currentRegion.getEndKey(), true);
    return true;
  }

  @Override
  protected ScannerCallable createScannerCallable() {
    if (!scan.includeStartRow() && !isEmptyStartRow(scan.getStartRow())) {
      // we have not implemented locate to next row for sync client yet, so here we change the
      // inclusive of start row to true.
      scan.withStartRow(createClosestRowAfter(scan.getStartRow()), true);
    }
    return new ScannerCallable(getConnection(), getTable(), scan, this.scanMetrics,
        this.rpcControllerFactory);
  }
}
