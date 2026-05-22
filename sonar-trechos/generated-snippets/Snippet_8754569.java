private static class RemoveAppTransition implements
      SingleArcTransition<RMStateStore, RMStateStoreEvent> {
    @Override
    public void transition(RMStateStore store, RMStateStoreEvent event) {
      if (!(event instanceof RMStateStoreRemoveAppEvent)) {
        // should never happen
        LOG.error("Illegal event type: " + event.getClass());
        return;
      }
      ApplicationState appState = ((RMStateStoreRemoveAppEvent) event)
          .getAppState();
      ApplicationId appId = appState.getAppId();
      LOG.info("Removing info for app: " + appId);
      try {
        store.removeApplicationStateInternal(appState);
      } catch (Exception e) {
        LOG.error("Error removing app: " + appId, e);
        store.notifyStoreOperationFailed(e);
      }
    };
  }
