public interface HttpEntity extends EntityDetails, Closeable {

    /**
     * Tells if the entity is capable of producing its data more than once.
     * A repeatable entity's getContent() and writeTo(OutputStream) methods
     * can be called more than once whereas a non-repeatable entity's can not.
     * @return true if the entity is repeatable, false otherwise.
     */
    boolean isRepeatable();

    /**
     * Returns a content stream of the entity.
     * {@link #isRepeatable Repeatable} entities are expected
     * to create a new instance of {@link InputStream} for each invocation
     * of this method and therefore can be consumed multiple times.
     * Entities that are not {@link #isRepeatable repeatable} are expected
     * to return the same {@link InputStream} instance and therefore
     * may not be consumed more than once.
     * <p>
     * IMPORTANT: Please note all entity implementations must ensure that
     * all allocated resources are properly deallocated after
     * the {@link InputStream#close()} method is invoked.
     * </p>
     * @return content stream of the entity.
     *
     * @throws IOException if the stream could not be created
     * @throws UnsupportedOperationException
     *  if entity content cannot be represented as {@link java.io.InputStream}.
     *
     * @see #isRepeatable()
     */
    InputStream getContent() throws IOException, UnsupportedOperationException;

    /**
     * Writes the entity content out to the output stream.
     * <p>
     * IMPORTANT: Please note all entity implementations must ensure that
     * all allocated resources are properly deallocated when this method
     * returns.
     * </p>
     *
     * @param outStream the output stream to write entity content to
     *
     * @throws IOException if an I/O error occurs
     */
    void writeTo(OutputStream outStream) throws IOException;

    /**
     * Tells whether this entity depends on an underlying stream.
     * Streamed entities that read data directly from the socket should
     * return {@code true}. Self-contained entities should return
     * {@code false}. Wrapping entities should delegate this call
     * to the wrapped entity.
     *
     * @return  {@code true} if the entity content is streamed,
     *          {@code false} otherwise
     */
    boolean isStreaming(); // don't expect an exception here

    /**
     * Returns supplier of message trailers - headers sent after message body.
     * May return {@code null} if trailers are not available.
     *
     * @since 5.0
     */
    Supplier<List<? extends Header>> getTrailers();

}
