@JsonIgnoreProperties(ignoreUnknown = true)
public class Realtime {

    private String uri;
    @JsonProperty("channel_id")
    private String channelId;
    @JsonProperty("authentication_token")
    private String authenticationToken;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    @Override
    public String toString() {
        return "Realtime [uri=" + uri + ", channelId=" + channelId + ", authenticationToken=" + authenticationToken + "]";
    }

}
