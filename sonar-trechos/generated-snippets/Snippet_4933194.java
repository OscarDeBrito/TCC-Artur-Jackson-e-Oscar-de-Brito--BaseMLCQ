public class Snippet__4933194 {

    private boolean exists( Group group )
        {
            String index = group.getName();
            try
            {
                findGroup( index );
                return true;
            }
            catch( NoSuchPrincipalException e )
            {
                return false;
            }
        }

}
