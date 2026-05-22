interface RecoveryEndpoint {
    Iterable<Object> getOptions();

    Persistence create();
  }
