private class EntrySetIterator implements Iterator<Entry<K, V2>> {
      long position = 0;

      @Override
      public boolean hasNext() {
        return position < size;
      }

      @Override
      public Entry<K, V2> next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }

        final K key;
        final V2 value;
        try {
          key =
              findMetadata(
                  readers,
                  ImmutableList.of(IsmFormat.getMetadataKey(), window, position + 1),
                  keyCoder);
          value = get(key);
        } catch (IOException e) {
          throw new IllegalStateException(e);
        }
        // Once we have fetched the key and value we can increment the position knowing that
        // an exception won't be thrown, thus allowing retries.
        position += 1;
        return new StructuralMapEntry<>(keyCoder, key, value);
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    }
