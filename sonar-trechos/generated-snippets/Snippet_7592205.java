public class Snippet__7592205 {

    protected void bindMetadataProvider(final MetadataProvider mp) {
        synchronized (lock) {
          Validate.notNull(mp, "Metadata provider required");
          final String mid = mp.getProvidesType();
          Validate.isTrue(MetadataIdentificationUtils.isIdentifyingClass(mid),
              "Metadata provider '%s' violated interface contract by returning '%s'", mp, mid);
          Validate.isTrue(!providerMap.containsKey(mid),
              "Metadata provider '%s' already is providing metadata for '%s'", providerMap.get(mid),
              mid);
          providers.add(mp);
          providerMap.put(mid, mp);
        }
      }

}
