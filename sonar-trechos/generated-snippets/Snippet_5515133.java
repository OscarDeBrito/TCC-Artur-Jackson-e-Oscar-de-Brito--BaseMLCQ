public final class StartHandler implements EventHandler<StartTime> {
    @Override
    public void onNext(final StartTime startTime) {
      try (final LoggingScope ls = loggingScopeFactory.driverStart(startTime)) {
        // CLR bridge setup must be done before other event handlers try to access the CLR bridge
        // thus we grab a lock on this instance
        synchronized (JobDriver.this) {
          setupBridge();
          LOG.log(Level.INFO, "Finished CLR bridge setup for {0}", startTime);
        }

        NativeInterop.callClrSystemOnStartHandler();
        LOG.log(Level.INFO, "Driver Started");
      }
    }
  }
