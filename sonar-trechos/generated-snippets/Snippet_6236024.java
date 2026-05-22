public class Snippet__6236024 {

    public Snippet__6236024(final GridFSFile dbFile, final String contentType,
                final Supplier<InputStream> inputStreamSupplier) {
            super(dbFile.getId().toString(), new DbArtifactHash(dbFile.getFilename(), dbFile.getMD5()), dbFile.getLength(),
                    contentType);
            this.inputStreamSupplier = inputStreamSupplier;
        }

}
