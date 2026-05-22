public static class UserToken implements HttpAuthToken {

    private String token;

    public UserToken(String token) {
      this.token = token;
    }

    public String getValue() {
      return token;
    }

    /**
     * Returns an authorization header to be used for a HTTP request
     * for the respective authentication token.
     *
     * @param requestUrl the URL being requested
     * @param requestMethod the HTTP method of the request
     * @return the "Authorization" header to be used for the request
     */
    public String getAuthorizationHeader(URL requestUrl,
                                         String requestMethod) {
      return "GoogleLogin auth=" + token;
    }
  }
