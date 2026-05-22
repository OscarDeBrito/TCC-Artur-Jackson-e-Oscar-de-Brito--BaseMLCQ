public class MemoryIterator extends WrappingIterator implements InterruptibleIterator {

    private AtomicBoolean closed;
    private SourceSwitchingIterator ssi;
    private MemoryDataSource mds;

    private MemoryIterator(InterruptibleIterator source) {
      this(source, new AtomicBoolean(false));
    }

    private MemoryIterator(SortedKeyValueIterator<Key,Value> source, AtomicBoolean closed) {
      setSource(source);
      this.closed = closed;
    }

    @Override
    public SortedKeyValueIterator<Key,Value> deepCopy(IteratorEnvironment env) {
      return new MemoryIterator(getSource().deepCopy(env), closed);
    }

    public void close() {

      synchronized (this) {
        if (closed.compareAndSet(false, true)) {
          try {
            if (mds.reader != null)
              mds.reader.close();
          } catch (IOException e) {
            log.warn("{}", e.getMessage(), e);
          }
        }
      }

      // remove outside of sync to avoid deadlock
      activeIters.remove(this);
    }

    private synchronized boolean switchNow() throws IOException {
      if (closed.get())
        return false;

      ssi.switchNow();
      return true;
    }

    @Override
    public void setInterruptFlag(AtomicBoolean flag) {
      ((InterruptibleIterator) getSource()).setInterruptFlag(flag);
    }

    private void setSSI(SourceSwitchingIterator ssi) {
      this.ssi = ssi;
    }

    public void setMDS(MemoryDataSource mds) {
      this.mds = mds;
    }

  }
