public class Snippet__4312069 {

    public static boolean isOid( String oidString )
        {
            try
            {
                Oid.fromString( oidString );

                return true;
            }
            catch ( DecoderException e )
            {
                return false;
            }
        }

}
