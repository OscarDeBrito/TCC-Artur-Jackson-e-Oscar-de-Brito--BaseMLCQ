final class WebsocketFinalizer extends HttpClient implements HttpClient.WebsocketSender {

	final TcpClient cachedConfiguration;

	WebsocketFinalizer(TcpClient parent) {
		this.cachedConfiguration = parent;
	}

	// UriConfiguration methods

	@Override
	public WebsocketSender uri(String uri) {
		return new WebsocketFinalizer(cachedConfiguration.bootstrap(b -> HttpClientConfiguration.uri(b, uri)));
	}

	@Override
	public WebsocketSender uri(Mono<String> uri) {
		return new WebsocketFinalizer(cachedConfiguration.bootstrap(b -> HttpClientConfiguration.deferredConf(b, conf -> uri.map(conf::uri))));
	}

	// WebsocketSender methods
	@Override
	public WebsocketFinalizer send(Function<? super HttpClientRequest, ? extends Publisher<Void>> sender) {
		Objects.requireNonNull(sender, "requestBody");
		return new WebsocketFinalizer(cachedConfiguration.bootstrap(b -> HttpClientConfiguration.body(b, (req, out) -> sender.apply(req))));
	}

	@Override
	@SuppressWarnings("unchecked")
	public Mono<WebsocketClientOperations> connect() {
		return (Mono<WebsocketClientOperations>)cachedConfiguration.connect();
	}

	@Override
	public ByteBufFlux receive() {
		return HttpClientFinalizer.content(cachedConfiguration, HttpClientFinalizer.contentReceiver);
	}

	@Override
	public <V> Flux<V> handle(BiFunction<? super WebsocketInbound, ? super WebsocketOutbound, ? extends Publisher<V>> receiver) {
		return connect().flatMapMany(c -> Flux.from(receiver.apply(c, c))
		                                      .doFinally(s -> HttpClientFinalizer.discard(c)));
	}
}
