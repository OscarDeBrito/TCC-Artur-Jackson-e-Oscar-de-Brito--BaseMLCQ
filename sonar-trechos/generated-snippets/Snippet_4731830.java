public class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler {

    /**
     * Handler for uncaughtException
     */
    public void uncaughtException(Thread thread, Throwable exception) {
      // Add try and catch block to prevent new exceptions stop the handling thread
      try {
        // Delegate to the actual one
        handleException(thread, exception);

        // SUPPRESS CHECKSTYLE IllegalCatch
      } catch (Throwable t) {
        LOG.log(Level.SEVERE, "Failed to handle exception. Process halting", t);
        Runtime.getRuntime().halt(1);
      }
    }

    // The actual uncaught exceptions handing logic
    private void handleException(Thread thread, Throwable exception) {
      // We would fail fast when errors occur
      if (exception instanceof Error) {
        LOG.log(Level.SEVERE,
            "Error caught in thread: " + thread.getName()
                + " with thread id: " + thread.getId() + ". Process halting...",
            exception);
        Runtime.getRuntime().halt(1);
      }

      // We would fail fast when exceptions happen in main thread
      if (thread.getId() == mainThreadId) {
        LOG.log(Level.SEVERE,
            "Exception caught in main thread. Process halting...",
            exception);
        Runtime.getRuntime().halt(1);
      }

      LOG.log(Level.SEVERE,
          "Exception caught in thread: " + thread.getName()
              + " with thread id: " + thread.getId(),
          exception);

      String sinkId = null;
      Integer thisSinkRetryAttempts = 0;

      // We enforced the name of thread running particular IMetricsSink equal to its sink-id
      // If the thread name is a key of SinkExecutors, then it is a thread running IMetricsSink
      if (sinkExecutors.containsKey(thread.getName())) {
        sinkId = thread.getName();
        // Remove the old sink executor
        SinkExecutor oldSinkExecutor = sinkExecutors.remove(sinkId);
        // Remove the unneeded Communicator bind with Metrics Manager Server
        metricsManagerServer.removeSinkCommunicator(oldSinkExecutor.getCommunicator());

        // Close the sink
        SysUtils.closeIgnoringExceptions(oldSinkExecutor);

        thisSinkRetryAttempts = sinksRetryAttempts.remove(sinkId);
      }

      if (sinkId != null && thisSinkRetryAttempts != 0) {
        LOG.info(String.format("Restarting IMetricsSink: %s with %d available retries",
            sinkId, thisSinkRetryAttempts));

        // That means it was a sinkExecutor throwing exceptions and threadName is sinkId
        SinkExecutor newSinkExecutor = initSinkExecutor(sinkId);

        // Update the SinkExecutor in sinkExecutors
        sinkExecutors.put(sinkId, newSinkExecutor);

        // Update the retry attempts if it is > 0
        if (thisSinkRetryAttempts > 0) {
          thisSinkRetryAttempts--;
        }
        sinksRetryAttempts.put(sinkId, thisSinkRetryAttempts);

        // Update the list of Communicator in Metrics Manager Server
        metricsManagerServer.addSinkCommunicator(newSinkExecutor.getCommunicator());

        // Restart it
        executors.execute(newSinkExecutor);
      } else if (sinkId != null
          && thisSinkRetryAttempts == 0
          && sinkExecutors.size() > 0) {
        // If the dead executor is the only one executor and it is removed,
        // e.g. sinkExecutors.size() == 0, we would exit the process directly

        LOG.severe("Failed to recover from exceptions for IMetricsSink: " + sinkId);
        LOG.info(sinkId + " would close and keep running rest sinks: " + sinkExecutors.keySet());
      } else {
        // It is not recoverable (retried too many times, or not an exception from IMetricsSink)
        // So we would do basic cleaning and exit
        LOG.info("Failed to recover from exceptions; Metrics Manager Exiting");
        for (Handler handler : java.util.logging.Logger.getLogger("").getHandlers()) {
          handler.close();
        }
        // Attempts to shutdown all the thread in threadsPool. This will send Interrupt to every
        // thread in the pool. Threads may implement a clean Interrupt logic.
        executors.shutdownNow();

        // (including threads not owned by HeronInstance). To be safe, not sending these
        // interrupts.
        Runtime.getRuntime().halt(1);
      }
    }
  }
