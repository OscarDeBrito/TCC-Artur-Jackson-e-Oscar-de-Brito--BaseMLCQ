public final class JwtIssuerValidator implements OAuth2TokenValidator<Jwt> {
	private static OAuth2Error INVALID_ISSUER =
			new OAuth2Error(
					OAuth2ErrorCodes.INVALID_REQUEST,
					"This iss claim is not equal to the configured issuer",
					"https://tools.ietf.org/html/rfc6750#section-3.1");

	private final String issuer;

	/**
	 * Constructs a {@link JwtIssuerValidator} using the provided parameters
	 *
	 * @param issuer - The issuer that each {@link Jwt} should have.
	 */
	public JwtIssuerValidator(String issuer) {
		Assert.notNull(issuer, "issuer cannot be null");
		this.issuer = issuer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OAuth2TokenValidatorResult validate(Jwt token) {
		Assert.notNull(token, "token cannot be null");

		String tokenIssuer = token.getClaimAsString(JwtClaimNames.ISS);
		if (this.issuer.equals(tokenIssuer)) {
			return OAuth2TokenValidatorResult.success();
		} else {
			return OAuth2TokenValidatorResult.failure(INVALID_ISSUER);
		}
	}
}
