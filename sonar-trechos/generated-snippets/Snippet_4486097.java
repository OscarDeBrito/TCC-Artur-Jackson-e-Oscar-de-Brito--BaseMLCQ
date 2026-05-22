private class DatabaseLoader extends CacheLoader<String, List<String>> {

    @Override
    public List<String> load(String key) throws Exception {
      if (!DATABASES.equals(key)) {
        throw new UnsupportedOperationException();
      }
      try {
        List<String> dbNames = new ArrayList<>();
        plugin.getClient().listDatabaseNames().into(dbNames);
        return dbNames;
      } catch (MongoException me) {
        logger.warn("Failure while loading databases in Mongo. {}",
            me.getMessage());
        return Collections.emptyList();
      } catch (Exception e) {
        throw new DrillRuntimeException(e.getMessage(), e);
      }
    }

  }
