public final class RawJsonDeserializer extends StdDeserializer<String> {

	private static final long serialVersionUID = -4089499607872996396L;

	protected RawJsonDeserializer() {
		super(String.class);
	}

	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		final JsonNode jsonNode = ctxt.readValue(p, JsonNode.class);

		return jsonNode.toString();
	}
}
