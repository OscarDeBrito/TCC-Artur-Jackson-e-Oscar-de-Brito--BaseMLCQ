public class Snippet__5127922 {

    private void deleteIndexFiles( boolean full )
            throws IOException
        {
            if ( indexDirectory != null )
            {
                String[] names = indexDirectory.listAll();

                if ( names != null )
                {

                    for ( String name : names )
                    {
                        if ( !( name.equals( INDEX_PACKER_PROPERTIES_FILE )
                            || name.equals( INDEX_UPDATER_PROPERTIES_FILE ) ) )
                        {
                            indexDirectory.deleteFile( name );
                        }
                    }
                }

                if ( full )
                {
                    try
                    {
                        indexDirectory.deleteFile( INDEX_PACKER_PROPERTIES_FILE );
                    }
                    catch ( IOException ioe )
                    {
                        //Does not exist
                    }

                    try
                    {
                        indexDirectory.deleteFile( INDEX_UPDATER_PROPERTIES_FILE );
                    }
                    catch ( IOException ioe )
                    {
                        //Does not exist
                    }
                }

                IndexUtils.deleteTimestamp( indexDirectory );
            }
        }

}
