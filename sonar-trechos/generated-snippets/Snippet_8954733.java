public class Snippet__8954733 {

    static DescriptiveStatistics benchmarkTableCreate(@NotNull MicroBenchmark bench,
                                                        @NotNull BenchData data) {
        final HMSClient client = data.getClient();
        String dbName = data.dbName;
        String tableName = data.tableName;
        Table table = Util.TableBuilder.buildDefaultTable(dbName, tableName);

        return bench.measure(null,
            () -> throwingSupplierWrapper(() -> client.createTable(table)),
            () -> throwingSupplierWrapper(() -> client.dropTable(dbName, tableName)));
      }

}
