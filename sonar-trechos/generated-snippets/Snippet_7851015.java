static class OplogEntryIdSet {
    private final IntOpenHashSet ints = new IntOpenHashSet((int) INVALID_ID);
    private final LongOpenHashSet longs = new LongOpenHashSet((int) INVALID_ID);

    public void add(long id) {
      if (id == 0) {
        throw new IllegalArgumentException();
      } else if (id > 0 && id <= 0x00000000FFFFFFFFL) {
        this.ints.add((int) id);
      } else {
        this.longs.add(id);
      }
    }

    public boolean contains(long id) {
      if (id >= 0 && id <= 0x00000000FFFFFFFFL) {
        return this.ints.contains((int) id);
      } else {
        return this.longs.contains(id);
      }
    }

    public int size() {
      return this.ints.size() + this.longs.size();
    }
  }
