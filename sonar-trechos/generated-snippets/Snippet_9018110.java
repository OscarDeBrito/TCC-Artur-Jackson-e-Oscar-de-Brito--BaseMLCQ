public class Snippet__9018110 {

    private void registerReporter(String registry, String name, String tag, SolrMetricReporter reporter) throws Exception {
        try {
          if (!reportersLock.tryLock(10, TimeUnit.SECONDS)) {
            throw new Exception("Could not obtain lock to modify reporters registry: " + registry);
          }
        } catch (InterruptedException e) {
          throw new Exception("Interrupted while trying to obtain lock to modify reporters registry: " + registry);
        }
        try {
          Map<String, SolrMetricReporter> perRegistry = reporters.get(registry);
          if (perRegistry == null) {
            perRegistry = new HashMap<>();
            reporters.put(registry, perRegistry);
          }
          if (tag != null && !tag.isEmpty()) {
            name = name + "@" + tag;
          }
          SolrMetricReporter oldReporter = perRegistry.get(name);
          if (oldReporter != null) { // close it
            log.info("Replacing existing reporter '" + name + "' in registry '" + registry + "': " + oldReporter.toString());
            oldReporter.close();
          }
          perRegistry.put(name, reporter);

        } finally {
          reportersLock.unlock();
        }
      }

}
