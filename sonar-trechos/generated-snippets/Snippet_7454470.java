public class Snippet__7454470 {

    <T> T lookup(Class<T> serviceClass, boolean wrapExceptions) {
            engine.checkState();
            if (cache.supportsService(serviceClass)) {
                try {
                    ensureCreated();
                } catch (Throwable t) {
                    if (wrapExceptions) {
                        throw PolyglotImpl.wrapGuestException(engine, t);
                    } else {
                        throw t;
                    }
                }
                return INSTRUMENT.getInstrumentationHandlerService(engine.instrumentationHandler, this, serviceClass);
            } else {
                return null;
            }
        }

}
