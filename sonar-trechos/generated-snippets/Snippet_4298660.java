public class Snippet__4298660 {

    public void timeout( Integer timeout ) throws ValidationException
        {
            if ( ( timeout < 0 ) || ( timeout >= Integer.MAX_VALUE ) )
            {
                String error = "timeout - invalid timeout value [" + timeout + "]";
                throw new ValidationException( GlobalErrIds.CONST_TIMEOUT_INVLD, error );
            }
        }

}
