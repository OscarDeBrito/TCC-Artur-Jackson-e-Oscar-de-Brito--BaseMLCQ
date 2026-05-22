public final class AppleSingleDecoderStream extends AppleForkedDecoderStream {
    /**
     * Creates a file from the provided AppleSingle data. Will overwrite any
     * existing file.
     *
     * @param file
     *        The File to write data and resource forks to
     */
    public AppleSingleDecoderStream(final File file) {
        super(file, AppleForkedConstants.MAGIC_APPLESINGLE);
    }

    /**
     * Creates a file from the provided AppleSingle data. Will overwrite any
     * existing file.
     *
     * @param filename
     *        The name of the file to write data and resource forks to
     */
    public AppleSingleDecoderStream(final String filename) {
        super(filename, AppleForkedConstants.MAGIC_APPLESINGLE);
    }
}
