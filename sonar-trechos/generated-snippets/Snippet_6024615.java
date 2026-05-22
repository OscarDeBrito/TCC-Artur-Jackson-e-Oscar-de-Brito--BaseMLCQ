public class Snippet__6024615 {

    @Override
        public CachingBuildActionExecuter<T> addProgressListener(org.gradle.tooling.events.ProgressListener listener, OperationType... operationTypes) {
            this.cacheKey.markInvalid();
            this.delegate.addProgressListener(listener, operationTypes);
            return this;
        }

}
