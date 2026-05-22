abstract class LineParser<T> {

  public static <S> LineParser<S> forType(PType<S> ptype) {
    return new SimpleLineParser<S>(ptype);
  }
  
  public static <K, V> LineParser<Pair<K, V>> forTableType(PTableType<K, V> ptt, String sep) {
    return new KeyValueLineParser<K, V>(ptt, sep); 
  }
  
  private MapFn<String, T> mapFn;
  
  public void initialize() {
    mapFn = getMapFn();
    mapFn.initialize();
  }
    
  public T parse(String line) {
    return mapFn.map(line);
  }
  
  protected abstract MapFn<String, T> getMapFn();
  
  private static <T> MapFn<String, T> getMapFnForPType(PType<T> ptype) {
    MapFn ret = null;
    if (String.class.equals(ptype.getTypeClass())) {
      ret = (MapFn) IdentityFn.getInstance();
    } else {
      // Check for a composite MapFn for the PType.
      // Note that this won't work for Avro-- need to solve that.
      ret = ptype.getInputMapFn();
      if (ret instanceof CompositeMapFn) {
        ret = ((CompositeMapFn) ret).getSecond();
      }
    }
    return ret;
  }
  
  private static class SimpleLineParser<S> extends LineParser<S> {

    private final PType<S> ptype;
    
    public SimpleLineParser(PType<S> ptype) {
      this.ptype = ptype;
    }

    @Override
    protected MapFn<String, S> getMapFn() {
      return getMapFnForPType(ptype);
    }
  }
  
  private static class KeyValueLineParser<K, V> extends LineParser<Pair<K, V>> {

    private final PTableType<K, V> ptt;
    private final String sep;
    
    public KeyValueLineParser(PTableType<K, V> ptt, String sep) {
      this.ptt = ptt;
      this.sep = sep;
    }

    @Override
    protected MapFn<String, Pair<K, V>> getMapFn() {
      final MapFn<String, K> keyMapFn = getMapFnForPType(ptt.getKeyType());
      final MapFn<String, V> valueMapFn = getMapFnForPType(ptt.getValueType());
      
      return new MapFn<String, Pair<K, V>>() {
        @Override
        public void initialize() {
          keyMapFn.initialize();
          valueMapFn.initialize();
        }
        
        @Override
        public Pair<K, V> map(String input) {
          List<String> kv = ImmutableList.copyOf(Splitter.on(sep).limit(1).split(input));
          if (kv.size() != 2) {
            throw new RuntimeException("Invalid input string: " + input);
          }
          return Pair.of(keyMapFn.map(kv.get(0)), valueMapFn.map(kv.get(1)));
        }
      };
    }
  }
}
