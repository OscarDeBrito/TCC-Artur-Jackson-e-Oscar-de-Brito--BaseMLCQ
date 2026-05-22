@Header("Content-Length")
public final class ContentLength extends HeaderLong {

	/**
	 * Constructor.
	 *
	 * @param value
	 */
	public ContentLength(Long value) {
		super(value);
	}

	/**
	 * Returns a parsed <code>Content-Length</code> header.
	 *
	 * @param value The <code>Content-Length</code> header string.
	 * @return The parsed <code>Content-Length</code> header, or <jk>null</jk> if the string was null.
	 */
	public static ContentLength forString(String value) {
		if (value == null)
			return null;
		return new ContentLength(value);
	}

	private ContentLength(String value) {
		super(value);
	}
}
