public class Snippet__6721230 {

    @Override
      public @Nullable Object dispatchOnEvent(EventHandler eventHandler, Object eventState) {
        if (ComponentsConfiguration.enableOnErrorHandling && eventHandler.id == ERROR_EVENT_HANDLER_ID) {
          ((Component) this).getErrorHandler().dispatchEvent(((ErrorEvent) eventState));
        }

        // Don't do anything by default, unless we're handling an error.
        return null;
      }

}
