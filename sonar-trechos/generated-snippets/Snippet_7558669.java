static class ExponentialRandomBackOffContext
			extends ExponentialBackOffPolicy.ExponentialBackOffContext {

		private final Random r = new Random();

		public ExponentialRandomBackOffContext(long expSeed, double multiplier,
				long maxInterval) {
			super(expSeed, multiplier, maxInterval);
		}

		@Override
		public synchronized long getSleepAndIncrement() {
			long next = super.getSleepAndIncrement();
			next = (long) (next * (1 + r.nextFloat() * (getMultiplier() - 1)));
			return next;
		}

	}
