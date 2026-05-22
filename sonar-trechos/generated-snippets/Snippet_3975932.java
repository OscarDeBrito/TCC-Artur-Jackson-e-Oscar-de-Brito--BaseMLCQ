abstract class Mixin<R extends Enum<R>>
        implements HasUoWFiles<R>
    {
        @Service
        private UoWFileFactory uowFileFactory;
        @This
        private UoWFilesLocator<R> locator;

        @Override
        public File attachedFile( R key )
        {
            return locator.locateAttachedFile( key );
        }

        @Override
        public Iterable<File> attachedFiles()
        {
            return locator.locateAttachedFiles();
        }

        @Override
        public File managedFile( R key )
        {
            return uowFileFactory.createCurrentUoWFile( locator.locateAttachedFile( key ) ).asFile();
        }

        @Override
        public Iterable<File> managedFiles()
        {
            List<File> managedFiles = new ArrayList<>();
            for( File eachAttachedFile : locator.locateAttachedFiles() )
            {
                managedFiles.add( uowFileFactory.createCurrentUoWFile( eachAttachedFile ).asFile() );
            }
            return managedFiles;
        }
    }
