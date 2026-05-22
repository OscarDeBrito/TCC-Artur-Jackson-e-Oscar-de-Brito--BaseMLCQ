public static class MinShortAggregator implements Aggregator<Short, Short> {

		private short min = Short.MAX_VALUE;

		@Override
		public void aggregate(Short value) {
			min = min(min, value);
		}

		@Override
		public void combine(Aggregator<Short, Short> other) {
			min = min(min, ((MinShortAggregator) other).min);
		}

		@Override
		public Short result() {
			return min;
		}
	}
