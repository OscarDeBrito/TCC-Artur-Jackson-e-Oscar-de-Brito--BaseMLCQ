public interface ApproxEqualsDetector<T> {
    /**
     * Checks if two objects are approximately equal.
     * @param lhs the left hand side object.
     * @param rhs the right hand side object.
     * @return {@code true} if the two objects are considered approximately
     * equals. {@code false} otherwise.
     */
    public boolean areObjectsApproxEquals(final T lhs, final T rhs);

    /**
     * @return the default tolerance for the type.
     */
    public Tolerance getDefaultTolerance();

    /**
     * Converts a string representation of the object into the object
     * represented by the class {@link #getTypeClass()}.
     * @param string the {@link String} to convert to an object.
     * @return the object.
     * @throws SmartUriException
     */
    public T convertStringToObject(final String string) throws SmartUriException;

    /**
     * @return the object {@link Class} this detector is used for.
     */
    public Class<?> getTypeClass();

    /**
     * @return the {@link IRI} for the XML schema type this detector is used
     * for.
     */
    public IRI getXmlSchemaUri();

    /**
     * Checks if two string representations of objects are approximately equal.
     * @param lhs the left hand side string object representation.
     * @param rhs the right hand side string object representation.
     * @return {@code true} if the two string object representations are
     * considered approximately equals. {@code false} otherwise.
     * @throws SmartUriException
     */
    public default boolean areApproxEquals(final String lhs, final String rhs) throws SmartUriException {
        final T object1 = convertStringToObject(lhs);
        final T object2 = convertStringToObject(rhs);
        return areObjectsApproxEquals(object1, object2);
    }
}
