@JsonDeserialize
@Value.Immutable
abstract class _ListIsolationSegmentSpacesRelationshipResponse {

    /**
     * The assigned spaces
     */
    @JsonProperty("data")
    @Nullable
    abstract List<Relationship> getData();

    /**
     * The links
     */
    @AllowNulls
    @JsonProperty("links")
    @Nullable
    abstract Map<String, Link> getLinks();

}
