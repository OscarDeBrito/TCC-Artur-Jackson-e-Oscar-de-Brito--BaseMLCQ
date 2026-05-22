public class FileSystemSpoolOutTarget implements SpoolOutTarget
{
    private final Path basePath;

    public FileSystemSpoolOutTarget ( final Path basePath )
    {
        this.basePath = basePath;
    }

    @Override
    public void spoolOut ( final String fileName, final String mimeType, final IOConsumer<OutputStream> streamConsumer ) throws IOException
    {
        final Path path = this.basePath.resolve ( fileName );
        Files.createDirectories ( path.getParent () );
        try ( OutputStream stream = new BufferedOutputStream ( Files.newOutputStream ( path ) ) )
        {
            streamConsumer.accept ( stream );
        }
    }

}
