public class InvalidPackaging
    extends ArchetypeException
{
    public InvalidPackaging()
    {
    }

    public InvalidPackaging( String msg )
    {
        super( msg );
    }

    public InvalidPackaging( Throwable cause )
    {
        super( cause );
    }

    public InvalidPackaging( String msg, Throwable cause )
    {
        super( msg, cause );
    }
}
