public class LateralGroupCacheJGListener
    extends LateralCacheJGListener
    implements ILateralCacheJGListener
{
    private static final Log log = LogFactory.getLog( LateralGroupCacheJGListener.class );

    /**
     * Constructor for the LateralGroupCacheJGListener object
     *
     * @param ilca
     */
    protected LateralGroupCacheJGListener( ILateralCacheAttributes ilca )
    {
        super( ilca );
        log.debug( "creating LateralGroupCacheJGListener" );
    }

    /**
     * Gets the instance attribute of the LateralGroupCacheJGListener class
     * @param ilca
     *
     * @return The instance value
     */
    public static ILateralCacheListener getInstance( ILateralCacheAttributes ilca )
    {
        //throws IOException, NotBoundException
        ILateralCacheListener ins = (ILateralCacheListener) instances
            .get( String.valueOf( ilca.getUdpMulticastAddr() ) );
        synchronized ( LateralGroupCacheJGListener.class )
        {
            if ( ins == null )
            {
                ins = new LateralGroupCacheJGListener( ilca );
                ins.init();
            }
            if ( log.isDebugEnabled() )
            {
                log.debug( "created new listener " + ilca.getUdpMulticastAddr() );
            }
            instances.put( String.valueOf( ilca.getUdpMulticastAddr() ), ins );
        }

        return ins;
    }

    // override for new funcitonality
    // lazy init is too slow, find a better way
    /**
     * Gets the cacheManager attribute of the LateralGroupCacheJGListener object
     */
    protected void ensureCacheManager()
    {
        try
        {
            if ( cacheMgr == null )
            {
                cacheMgr = CompositeCacheManager.getInstance();
                if ( log.isDebugEnabled() )
                {
                    log.debug( " groupcache cacheMgr = " + cacheMgr );
                }
            }
            else
            {
                if ( log.isDebugEnabled() )
                {
                    log.debug( "already got groupcache cacheMgr = " + cacheMgr );
                }
            }
        }
        catch ( Exception e )
        {
            log.error( e );
        }
    }

}
