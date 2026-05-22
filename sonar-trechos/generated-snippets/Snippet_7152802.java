public final class SimpeTokenParser implements TokenParser {
	private static final TokenParser INSTANCE = new SimpeTokenParser();

	private final SignatureFactory signatureFactory = SimpleSignatureFactory.getInstance();

	private final TokenFactory tokenFactory = SimpleTokenFactory.getInstance();

	private final TimeStampFactory timeStampFactory = SimpleTimeStampFactory.getInstance();

	private final IssuerFactory issuerFactory = SimpleIssuerFactory.getInstance();

	private final IDFactory iDFactory = SimpleIDFactory.getInstance();

	private final NameFactory nameFactory = SimpleNameFactory.getInstance();

	private final AlgorithmFactory algorithmFactory = SimpleAlgorithmFactory.getInstanc();

	private final PayloadFactory payloadFactory = SimplePayloadFactory.getInstance();

	private final EmailFactory emailFactory = SimpleEmailFactory.getInstance();

	@Override
	public Token getToken(String value) {
		final String[] parts = value.split("\\.");
		if (parts.length != 3) {
			throw new IllegalStateException(
					String.format("Incorrect number of parts: Expected 3 got %s", parts.length));
		}

		final JsonNode header = parsePart(decodePart(parts[0]));
		final JsonNode body = parsePart(decodePart(parts[1]));

		final Name keyName = getKeyName(header);
		final Algorithm algorithm = getAlgorithm(header);

		final TimeStamp issuedAt = getIssuedAt(body);
		final TimeStamp notBefore = getNotBefore(body);
		final TimeStamp expiration = getExpiration(body);
		final Issuer issuer = getIssuer(body);
		final ID audience = getAudience(body);
		final ID userID = getUserID(body);
		final List<Email> userEmails = getEmails(body);

		final Payload payload = getPayload(parts[0], parts[1]);

		final Signature signature = getSignature(parts[2]);

		return tokenFactory.createToken(keyName, algorithm, issuedAt, notBefore, expiration, userID, userEmails, issuer,
				audience, payload, signature);
	}

	private ID getUserID(final JsonNode node) {
		final String value = node.has("oid") ? node.get("oid").asText() : null;
		return iDFactory.createID(value);
	}

	private List<Email> getEmails(final JsonNode node) {
		final List<Email> emails = new ArrayList<Email>();
		for (final JsonNode n : node.get("emails")) {
			emails.add(emailFactory.createEmail(n.asText()));
		}
		return emails;
	}

	private Payload getPayload(final String header, final String body) {
		return payloadFactory.createPayload(header, body);
	}

	private Algorithm getAlgorithm(final JsonNode node) {
		final String value = node.has("alg") ? node.get("alg").asText() : null;
		return algorithmFactory.createAlgorithm(value);
	}

	private Name getKeyName(final JsonNode node) {
		final String value = node.has("kid") ? node.get("kid").asText() : null;
		return nameFactory.createKeyName(value);
	}

	private ID getAudience(final JsonNode node) {
		final String value = node.has("aud") ? node.get("aud").asText() : null;
		return iDFactory.createID(value);
	}

	private Issuer getIssuer(final JsonNode node) {
		final String value = node.has("iss") ? node.get("iss").asText() : null;
		return issuerFactory.createIssuer(value);
	}

	private TimeStamp getExpiration(final JsonNode node) {
		final Long time = node.has("exp") ? node.get("exp").asLong() : Long.MAX_VALUE;
		return timeStampFactory.createTimeStamp(time);
	}

	private TimeStamp getNotBefore(final JsonNode node) {
		final Long time = node.has("nbf") ? node.get("nbf").asLong() : 0;
		return timeStampFactory.createTimeStamp(time);
	}

	private TimeStamp getIssuedAt(final JsonNode node) {
		final Long time = node.has("iat") ? node.get("iat").asLong() : 0;
		return timeStampFactory.createTimeStamp(time);
	}

	private Signature getSignature(final String value) {
		return signatureFactory.createSignature(value);
	}

	private String decodePart(final String part) {
		if (part == null) {
			throw new PreconditionException("Required parameter is null");
		}
		try {
			final Base64 decoder = new Base64();
			return new String(decoder.decode(part), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new GeneralException("Unsupported Encoding Exception", e);
		}
	}

	private JsonNode parsePart(final String part) {
		if (part == null) {
			throw new PreconditionException("Required parameter is null");
		}
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(part, JsonNode.class);
		} catch (IOException e) {
			throw new GeneralException("IO Exception", e);
		}
	}

	public static TokenParser getInstance() {
		return INSTANCE;
	}
}
