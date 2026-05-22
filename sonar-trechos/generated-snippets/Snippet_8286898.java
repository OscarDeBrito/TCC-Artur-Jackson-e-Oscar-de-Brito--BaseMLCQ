public final class NoOpStageHandler implements StageHandler {

    @Override
    public <I, E extends Throwable> void onError(I injectee, E error) {
        // do nothing
    }

    @Override
    public <I> void onSuccess(I injectee) {
        // do nothing
    }

}
