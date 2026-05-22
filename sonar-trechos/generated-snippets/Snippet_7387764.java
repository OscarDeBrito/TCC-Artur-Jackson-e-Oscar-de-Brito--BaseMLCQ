static class CompositeConversionService implements ConversionService {

		private final List<ConversionService> delegates;

		CompositeConversionService(List<ConversionService> delegates) {
			this.delegates = delegates;
		}

		@Override
		public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
			Assert.notNull(targetType, "Target type to convert to cannot be null");
			return canConvert(
					(sourceType != null) ? TypeDescriptor.valueOf(sourceType) : null,
					TypeDescriptor.valueOf(targetType));
		}

		@Override
		public boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType) {
			for (ConversionService service : this.delegates) {
				if (service.canConvert(sourceType, targetType)) {
					return true;
				}
			}
			return false;
		}

		@Override
		@SuppressWarnings("unchecked")
		public <T> T convert(Object source, Class<T> targetType) {
			Assert.notNull(targetType, "Target type to convert to cannot be null");
			return (T) convert(source, TypeDescriptor.forObject(source),
					TypeDescriptor.valueOf(targetType));
		}

		@Override
		public Object convert(Object source, TypeDescriptor sourceType,
				TypeDescriptor targetType) {
			for (int i = 0; i < this.delegates.size() - 1; i++) {
				try {
					ConversionService delegate = this.delegates.get(i);
					if (delegate.canConvert(sourceType, targetType)) {
						return delegate.convert(source, sourceType, targetType);
					}
				}
				catch (ConversionException ex) {
				}
			}
			return this.delegates.get(this.delegates.size() - 1).convert(source,
					sourceType, targetType);
		}

	}
