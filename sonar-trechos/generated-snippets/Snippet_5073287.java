public class SystemUtils
{
    private static Logger log = LoggerFactory.getLogger(SystemUtils.class.getName());
    // Unfortunately, this tends to cause confusing logging.
    private static boolean logging = false ;
    
    static public ClassLoader chooseClassLoader()
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    
        if ( logging && classLoader != null )
            log.trace("Using thread classloader") ;
        
//        if (classLoader == null)
//        {
//            classLoader = this.getClass().getClassLoader();
//            if ( classLoader != null )
//                logger.trace("Using 'this' classloader") ;
//        }
        
        if ( classLoader == null )
        {
            classLoader = ClassLoader.getSystemClassLoader() ;
            if ( logging && classLoader != null )
                log.trace("Using system classloader") ;
        }
        
        if ( classLoader == null )
            throw new AtlasException("Failed to find a classloader") ;
        return classLoader ;
    }
    
}
