public class Snippet__7009087 {

    private Snippet__7009087(
          @Nullable Class<C> component,
          Provider<? extends RequestComponentBuilder<C>> requestComponentBuilderProvider,
          RequestAuthenticator requestAuthenticator) {
        // If the component class isn't explicitly provided, infer it from the class's own typing.
        // This is safe only for use by subclasses of RequestHandler where the generic parameter is
        // preserved at runtime, so only expose that option via the protected constructor.
        this.router = Router.create(
            component != null ? component : new TypeInstantiator<C>(getClass()){}.getExactType());
        this.requestComponentBuilderProvider = checkNotNull(requestComponentBuilderProvider);
        this.requestAuthenticator = checkNotNull(requestAuthenticator);
      }

}
