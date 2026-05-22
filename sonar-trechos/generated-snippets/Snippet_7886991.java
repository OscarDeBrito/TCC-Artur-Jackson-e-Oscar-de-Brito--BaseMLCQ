public static class Miss extends Cache {
      public final boolean cacheWasEmpty;

      public Miss(boolean cacheWasEmpty) {
        super("ActionGraphCacheMiss");
        this.cacheWasEmpty = cacheWasEmpty;
      }
    }
