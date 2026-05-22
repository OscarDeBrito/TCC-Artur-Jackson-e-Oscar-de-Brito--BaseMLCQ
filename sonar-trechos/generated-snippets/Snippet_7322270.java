public interface IValidationMessage {
    /**
     * @return this {@link IValidationMessage}'s {@link String} message (may be
     *         <code>null</code>)
     */
    public String getMessage();

    /**
     * Obtains a localized version of this {@link IValidationMessage}'s
     * {@link String} message (optional operation). If this
     * {@link IValidationMessage} implementation does not support this
     * operation, this method returns the same result as {@link #getMessage()}.
     *
     * @param locale
     *        a {@link Locale} to obtain the message for or <code>null</code> to
     *        use the default {@link Locale}
     * @return a localized message
     */
    public String getLocalizedMessage(Locale locale);

    /**
     * @return the {@link Severity} of this {@link IValidationMessage} (never
     *         <code>null</code>)
     */
    public Severity getSeverity();
}
