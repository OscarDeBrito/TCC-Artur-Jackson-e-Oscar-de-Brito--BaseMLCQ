@ProviderType
public interface Authentication {

    /**
     * Validates the specified {@code Credentials} and returns {@code true} if
     * the validation was successful.
     *
     * @param credentials to verify
     * @return {@code true} if the validation was successful; {@code false}
     * if the specified credentials are not supported and this authentication
     * implementation cannot verify their validity.
     * @throws LoginException if the authentication failed.
     */
    boolean authenticate(@Nullable Credentials credentials) throws LoginException;

    /**
     * Optional method that return the userID extracted upon {@link #authenticate(Credentials)}.
     * It is expected to return {@code null} if the implementation doesn't support this.
     *
     * An {@link IllegalStateException} may be thrown if called prior to {@link #authenticate(Credentials)}.
     *
     * @return a user identifier or {@code null}
     */
    @Nullable
    String getUserId();

    /**
     * Optional method that return the {@link Principal} of the authenticating user
     * extracted upon {@link #authenticate(Credentials)}. It is expected to return
     * {@code null} if the implementation doesn't support this.
     *
     * An {@link IllegalStateException} may be thrown if called prior to {@link #authenticate(Credentials)}.
     *
     * @return a valid {@code Principal} or {@code null}
     */
    @Nullable
    Principal getUserPrincipal();
}
