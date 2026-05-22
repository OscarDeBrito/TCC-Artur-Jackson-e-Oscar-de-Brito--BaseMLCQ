private static class ConservativeProviderFutureFactory extends ProviderFutureFactory {

        @Override
        public ProviderFuture createFuture() {
            return new ConservativeProviderFuture();
        }

        @Override
        public ProviderFuture createFuture(ProviderSynchronization synchronization) {
            return new ConservativeProviderFuture(synchronization);
        }

        @Override
        public ProviderFuture createUnfailableFuture() {
            return new ConservativeProviderFuture() {

                @Override
                public void onFailure(Throwable t) {
                    this.onSuccess();
                }
            };
        }
    }
