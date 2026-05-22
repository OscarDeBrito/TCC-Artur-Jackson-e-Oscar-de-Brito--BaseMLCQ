static class Lazy {
        static final ThreadLocal<TruffleRuntimeOptionsOverrideScope> overrideScope = new ThreadLocal<>();
    }
