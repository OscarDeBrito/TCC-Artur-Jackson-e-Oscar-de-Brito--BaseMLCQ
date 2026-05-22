public interface PlaybackNearlyFinishedRequestHandler extends RequestHandler {

    /**
     * Returns true if the handler can dispatch the current request
     *
     * @param input input to the request handler
     * @param playbackNearlyFinishedRequest PlaybackNearlyFinishedRequest request
     * @return true if the handler is capable of handling the current request and/or state
     */
    boolean canHandle(HandlerInput input, PlaybackNearlyFinishedRequest playbackNearlyFinishedRequest);

    /**
     * Handles the request.
     *
     * @param input input to the request handler
     * @param playbackNearlyFinishedRequest PlaybackNearlyFinishedRequest request
     * @return output from the handler.
     */
    Optional<Response> handle(HandlerInput input, PlaybackNearlyFinishedRequest playbackNearlyFinishedRequest);

    @Override
    default boolean canHandle(HandlerInput handlerInput) {
        if (handlerInput.getRequest() instanceof PlaybackNearlyFinishedRequest) {
            return canHandle(handlerInput, (PlaybackNearlyFinishedRequest)handlerInput.getRequest());
        }
        return false;
    }

    @Override
    default Optional<Response> handle(HandlerInput handlerInput) {
        return handle(handlerInput, (PlaybackNearlyFinishedRequest)handlerInput.getRequest());
    }

}
