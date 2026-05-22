public class Snippet__4887547 {

    public InputStream getStream() throws RepositoryException {
                // if the value has not yet been loaded -> retrieve it first in
                // order to make sure that either 'file' or 'buffer' is set.
                if (file == null && buffer == null) {
                    try {
                        loadBinary();
                    } catch (IOException e) {
                        throw new RepositoryException(e);
                    }
                }

                // always return a 'fresh' stream
                if (file != null) {
                    // this instance is backed by a 'real' file
                    try {
                        return new FileInputStream(file);
                    } catch (FileNotFoundException fnfe) {
                        throw new RepositoryException("file backing binary value not found",
                            fnfe);
                    }
                } else {
                    return new ByteArrayInputStream(buffer);
                }
            }

}
