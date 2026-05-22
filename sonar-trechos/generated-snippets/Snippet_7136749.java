private class SymlinkDataCallback extends SymlinkWatcher implements AsyncCallback.DataCallback
  {
    private final AsyncCallback.DataCallback _callback;

    public SymlinkDataCallback (String rawPath, Watcher watch, AsyncCallback.DataCallback cb)
    {
      super(watch, rawPath);
      _callback = cb;
    }

    @Override
    public void processResult(int rc, String path, Object ctx, byte[] bytes, Stat stat)
    {
      _callback.processResult(rc, _rawPath, ctx, bytes, stat);
      _callbackInvoked = true;
      // flush out the pending watch event if necessary.
      if (_pendingEvent != null)
      {
        _watch.process(_pendingEvent);
      }
    }
  }
