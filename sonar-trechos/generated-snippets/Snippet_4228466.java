public class JarArchiveInputStream extends ZipArchiveInputStream {

    /**
     * Creates an instance from the input stream using the default encoding.
     *
     * @param inputStream the input stream to wrap
     */
    public JarArchiveInputStream( final InputStream inputStream ) {
        super(inputStream);
    }

    /**
     * Creates an instance from the input stream using the specified encoding.
     *
     * @param inputStream the input stream to wrap
     * @param encoding the encoding to use
     * @since 1.10
     */
    public JarArchiveInputStream( final InputStream inputStream, final String encoding ) {
        super(inputStream, encoding);
    }

    public JarArchiveEntry getNextJarEntry() throws IOException {
        final ZipArchiveEntry entry = getNextZipEntry();
        return entry == null ? null : new JarArchiveEntry(entry);
    }

    @Override
    public ArchiveEntry getNextEntry() throws IOException {
        return getNextJarEntry();
    }

    /**
     * Checks if the signature matches what is expected for a jar file
     * (in this case it is the same as for a zip file).
     *
     * @param signature
     *            the bytes to check
     * @param length
     *            the number of bytes to check
     * @return true, if this stream is a jar archive stream, false otherwise
     */
    public static boolean matches(final byte[] signature, final int length ) {
        return ZipArchiveInputStream.matches(signature, length);
    }
}
