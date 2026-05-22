@Deprecated
public class ProgressLogger extends ProgressMonitor
{
    public ProgressLogger(Logger log, String label, long tickPoint, int superTick)
    {
        super(label, tickPoint, superTick, (fmt, args)->print(log, fmt, args) ) ;
    }
    
    /** Print a message in the form for this ProgressLogger */ 
    static void print(Logger log, String fmt, Object...args)
    {
        if ( log != null && log.isInfoEnabled() )
        {
            String str = String.format(fmt, args) ;
            log.info(str) ;
        }
    }
}
