public class Snippet__7409026 {

    @Nullable
    	protected PdxSerializer resolveCustomPdxSerializer(@NonNull PersistentProperty<?> property) {

    		Map<?, PdxSerializer> customPdxSerializers = getCustomPdxSerializers();

    		return this.pdxSerializerResolvers.stream()
    			.map(it -> it.resolve(customPdxSerializers, property))
    			.filter(Objects::nonNull)
    			.findFirst()
    			.orElse(null);
    	}

}
