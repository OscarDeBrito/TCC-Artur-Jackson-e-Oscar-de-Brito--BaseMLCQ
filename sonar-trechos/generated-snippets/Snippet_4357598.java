public class Snippet__4357598 {

    private  static  Object bootServiceModule
            (
             final boolean create, final Object serviceModule,
             final String factoryInterface, final Properties properties
             )
            throws StandardException
        {
            try {
                return AccessController.doPrivileged
                    (
                     new PrivilegedExceptionAction<Object>()
                     {
                         public Object run()
                             throws StandardException
                         {
                             return Monitor.bootServiceModule( create, serviceModule, factoryInterface, properties );
                         }
                     }
                     );
            } catch (PrivilegedActionException pae)
            {
                throw StandardException.plainWrapException( pae );
            }
        }

}
