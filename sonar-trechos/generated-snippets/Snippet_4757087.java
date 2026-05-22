@Slf4j
public class JsonCredentialStore implements CredentialStore {
  private static final ObjectMapper defaultMapper = new ObjectMapper();
  public final static String TAG = "json";

  private Map<String, byte[]> credentials;

  /**
   * Instantiate a new keystore using the file at the provided path
   */
  public JsonCredentialStore(String path, KeyToStringCodec codec) throws IOException {
    this(new Path(path), codec);
  }

  /**
   * Instantiate a new keystore using the file at the provided path
   */
  public JsonCredentialStore(Path path, KeyToStringCodec codec) throws IOException {
    credentials =  new HashMap<>();

    FileSystem fs = path.getFileSystem(new Configuration());
    try (InputStream in = fs.open(path)) {
      ObjectMapper jsonParser = defaultMapper;
      JsonNode tree = jsonParser.readTree(in);
      if (!tree.isObject()) {
        throw new IllegalArgumentException("Json in " + path.toString() + " is not an object!");
      }

      Iterator<Map.Entry<String, JsonNode>> it = tree.getFields();
      while (it.hasNext()) {
        Map.Entry<String, JsonNode> field = it.next();
        String keyId = field.getKey();
        byte[] key = codec.decodeKey(field.getValue().getTextValue());

        credentials.put(keyId, key);
      }
    }

    log.info("Initialized keystore from {} with {} keys", path.toString(), credentials.size());
  }

  @Override
  public byte[] getEncodedKey(String id) {
    return credentials.get(id);
  }

  @Override
  public Map<String, byte[]> getAllEncodedKeys() {
    return Collections.unmodifiableMap(credentials);
  }
}
