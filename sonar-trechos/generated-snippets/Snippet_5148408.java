@Deprecated
public class AsynchronousRunner
    implements RunnerScheduler
{
    private final List<Future<Object>> futures = Collections.synchronizedList( new ArrayList<Future<Object>>() );

    private final ExecutorService fService;

    public AsynchronousRunner( ExecutorService fService )
    {
        this.fService = fService;
    }

    @Override
    public void schedule( final Runnable childStatement )
    {
        futures.add( fService.submit( Executors.callable( childStatement ) ) );
    }


    @Override
    public void finished()
    {
        try
        {
            waitForCompletion();
        }
        catch ( ExecutionException e )
        {
            throw new RuntimeException( e );
        }
    }

    public void waitForCompletion()
        throws ExecutionException
    {
        for ( Future<Object> each : futures )
        {
            try
            {
                each.get();
            }
            catch ( InterruptedException e )
            {
                throw new RuntimeException( e );
            }
        }
    }
}
