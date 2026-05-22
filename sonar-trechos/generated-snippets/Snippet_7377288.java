public class CompositeReactiveHealthIndicator implements ReactiveHealthIndicator {

	private final ReactiveHealthIndicatorRegistry registry;

	private final HealthAggregator healthAggregator;

	private Long timeout;

	private Health timeoutHealth;

	private final Function<Mono<Health>, Mono<Health>> timeoutCompose;

	/**
	 * Create a new {@link CompositeReactiveHealthIndicator} from the indicators in the
	 * given {@code registry}.
	 * @param healthAggregator the health aggregator
	 * @param registry the registry of {@link ReactiveHealthIndicator HealthIndicators}.
	 */
	public CompositeReactiveHealthIndicator(HealthAggregator healthAggregator,
			ReactiveHealthIndicatorRegistry registry) {
		this.registry = registry;
		this.healthAggregator = healthAggregator;
		this.timeoutCompose = (mono) -> (this.timeout != null) ? mono.timeout(
				Duration.ofMillis(this.timeout), Mono.just(this.timeoutHealth)) : mono;
	}

	/**
	 * Specify an alternative timeout {@link Health} if a {@link HealthIndicator} failed
	 * to reply after specified {@code timeout}.
	 * @param timeout number of milliseconds to wait before using the
	 * {@code timeoutHealth}
	 * @param timeoutHealth the {@link Health} to use if an health indicator reached the
	 * {@code timeout}
	 * @return this instance
	 */
	public CompositeReactiveHealthIndicator timeoutStrategy(long timeout,
			Health timeoutHealth) {
		this.timeout = timeout;
		this.timeoutHealth = (timeoutHealth != null) ? timeoutHealth
				: Health.unknown().build();
		return this;
	}

	ReactiveHealthIndicatorRegistry getRegistry() {
		return this.registry;
	}

	@Override
	public Mono<Health> health() {
		return Flux.fromIterable(this.registry.getAll().entrySet())
				.flatMap((entry) -> Mono.zip(Mono.just(entry.getKey()),
						entry.getValue().health().compose(this.timeoutCompose)))
				.collectMap(Tuple2::getT1, Tuple2::getT2)
				.map(this.healthAggregator::aggregate);
	}

}
