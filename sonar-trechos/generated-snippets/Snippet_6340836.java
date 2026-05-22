public static class Factory implements TypeAdapterFactory {

		@SuppressWarnings("unchecked")
		@Override
		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
			return (TypeAdapter<T>) new JsonElementTypeAdapter(gson);
		}
		
	}
