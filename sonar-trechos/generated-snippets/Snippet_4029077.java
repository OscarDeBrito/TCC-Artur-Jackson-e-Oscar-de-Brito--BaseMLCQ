public class Snippet__4029077 {

    @AutoValue.Builder
        abstract static class Builder {
          abstract Builder setConfigProperties(Map<String, String> configProperties);

          abstract Builder setDatabase(String database);

          abstract Builder setTable(String table);

          abstract Builder setFilter(String filter);

          abstract Builder setSplitId(Integer splitId);

          abstract Builder setContext(ReaderContext context);

          abstract Read build();
        }

}
