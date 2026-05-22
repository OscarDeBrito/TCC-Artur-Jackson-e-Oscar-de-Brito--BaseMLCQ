@JsonDeserialize(builder = SetFilterRequest.Builder.class)
public class SetFilterRequest implements JmapRequest {

    private static final String ISSUER = "SetFilterRequest";

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private final ImmutableList.Builder<JmapRuleDTO> rules;

        private Builder() {
            this.rules = ImmutableList.builder();
        }

        public Builder accountId(String accountId) {
            if (accountId != null) {
                throw new JmapFieldNotSupportedException(ISSUER, "accountId");
            }
            return this;
        }

        public Builder ifInState(String ifInState) {
            if (ifInState != null) {
                throw new JmapFieldNotSupportedException(ISSUER, "ifInState");
            }
            return this;
        }

        public Builder singleton(List<JmapRuleDTO> rules) {
            this.rules.addAll(rules);
            return this;
        }

        public SetFilterRequest build() {
            return new SetFilterRequest(rules.build());
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final List<JmapRuleDTO> singleton;

    private SetFilterRequest(List<JmapRuleDTO> singleton) {
        this.singleton = singleton;
    }

    public List<JmapRuleDTO> getSingleton() {
        return singleton;
    }
}
