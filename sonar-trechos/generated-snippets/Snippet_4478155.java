@JsonTypeName(MongoStoragePluginConfig.NAME)
public class MongoStoragePluginConfig extends StoragePluginConfig {
  static final org.slf4j.Logger logger = org.slf4j.LoggerFactory
      .getLogger(MongoStoragePluginConfig.class);

  public static final String NAME = "mongo";

  private String connection;

  @JsonIgnore
  private MongoClientURI clientURI;

  @JsonCreator
  public MongoStoragePluginConfig(@JsonProperty("connection") String connection) {
    this.connection = connection;
    this.clientURI = new MongoClientURI(connection);
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    } else if (that == null || getClass() != that.getClass()) {
      return false;
    }
    MongoStoragePluginConfig thatConfig = (MongoStoragePluginConfig) that;
    return this.connection.equals(thatConfig.connection);

  }

  @Override
  public int hashCode() {
    return this.connection != null ? this.connection.hashCode() : 0;
  }

  @JsonIgnore
  public MongoCredential getMongoCrendials() {
    return clientURI.getCredentials();
  }

  @JsonIgnore
  public MongoClientOptions getMongoOptions() {
    return clientURI.getOptions();
  }

  @JsonIgnore
  public List<String> getHosts() {
    return clientURI.getHosts();
  }

  public String getConnection() {
    return connection;
  }
}
