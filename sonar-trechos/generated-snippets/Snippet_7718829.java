private static class VmdkDatastoreDiscoveredCallbackHandler extends
            AbstractCallbackServiceHandler {

        private final BiConsumer<CallbackServiceHandlerState, ServiceErrorResponse> consumer;

        public VmdkDatastoreDiscoveredCallbackHandler(
                BiConsumer<CallbackServiceHandlerState, ServiceErrorResponse> consumer) {

            this.consumer = consumer;
        }

        @Override
        protected void handleFailedStagePatch(CallbackServiceHandlerState state) {
            ServiceErrorResponse err = state.taskInfo.failure;
            logWarning("Failed updating host info");
            if (err != null && err.stackTrace != null) {
                logFine("Task failure stack trace: %s", err.stackTrace);
                logWarning("Task failure error message: %s", err.message);
                consumer.accept(state, err);

                if (completionCallback != null) {
                    completionCallback.run();
                }
            }
        }

        @Override
        protected void handleFinishedStagePatch(CallbackServiceHandlerState state) {
            consumer.accept(state, null);

            if (completionCallback != null) {
                completionCallback.run();
            }
        }
    }
