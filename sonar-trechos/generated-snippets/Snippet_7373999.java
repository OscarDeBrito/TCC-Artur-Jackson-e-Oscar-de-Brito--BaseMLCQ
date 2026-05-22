@FunctionalInterface
public interface ReactiveHealthIndicator {

	/**
	 * Provide the indicator of health.
	 * @return a {@link Mono} that provides the {@link Health}
	 */
	Mono<Health> health();

}
