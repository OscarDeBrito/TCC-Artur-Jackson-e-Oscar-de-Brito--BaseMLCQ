@JsonIgnoreProperties(ignoreUnknown = true)
abstract class UpdateContentGroupMixin extends LinkedInObjectMixin {

	@JsonCreator
	UpdateContentGroupMixin (
		@JsonProperty("id") String id, 
		@JsonProperty("firstName") String firstName, 
		@JsonProperty("lastName") String lastName, 
		@JsonProperty("headline") String headline, 
		@JsonProperty("industry") String industry, 
		@JsonProperty("publicProfileUrl") String publicProfileUrl, 
		@JsonProperty("siteStandardProfileRequest") UrlResource siteStandardProfileRequest, 
		@JsonProperty("pictureUrl") String profilePictureUrl) {}
	
	@JsonProperty("memberGroups")
	@JsonDeserialize(using=MemberGroupsListDeserializer.class)
	List<MemberGroup> memberGroups;
	
	private static class MemberGroupsListDeserializer extends JsonDeserializer<List<MemberGroup>> {
		@SuppressWarnings("unchecked")
		@Override
		public List<MemberGroup> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new LinkedInModule());
			jp.setCodec(mapper);
			if(jp.hasCurrentToken()) {
				JsonNode dataNode = jp.readValueAs(JsonNode.class).get("values");
				return (List<MemberGroup>) mapper.reader(new TypeReference<List<MemberGroup>>() {}).readValue(dataNode);
			}
			
			return null;
		}
	}

}
