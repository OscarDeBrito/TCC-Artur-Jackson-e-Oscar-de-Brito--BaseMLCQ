private static class JacksonInputMapFn<T> extends MapFn<String, T> {

    private final Class<T> clazz;
    private transient ObjectMapper mapper;

    JacksonInputMapFn(Class<T> clazz) {
      this.clazz = clazz;
    }

    @Override
    public void initialize() {
      this.mapper = new ObjectMapper();
    }

    @Override
    public T map(String input) {
      try {
        return mapper.readValue(input, clazz);
      } catch (Exception e) {
        throw new CrunchRuntimeException(e);
      }
    }
  }
