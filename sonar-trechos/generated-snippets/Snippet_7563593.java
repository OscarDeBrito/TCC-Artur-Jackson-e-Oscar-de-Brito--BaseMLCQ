private static class UniquePredicate<A extends Annotation, K> implements Predicate<MergedAnnotation<A>> {

		private final Function<? super MergedAnnotation<A>, K> keyExtractor;

		private final Set<K> seen = new HashSet<>();

		UniquePredicate(Function<? super MergedAnnotation<A>, K> keyExtractor) {
			Assert.notNull(keyExtractor, "Key extractor must not be null");
			this.keyExtractor = keyExtractor;
		}

		@Override
		public boolean test(@Nullable MergedAnnotation<A> annotation) {
			K key = this.keyExtractor.apply(annotation);
			return this.seen.add(key);
		}

	}
