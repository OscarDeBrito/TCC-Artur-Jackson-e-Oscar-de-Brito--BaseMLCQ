public class Snippet__5150385 {

    private static ScheduledExecutorService createTimeoutCheckScheduler()
        {
            ThreadFactory threadFactory = newDaemonThreadFactory( "timeout-check-timer" );
            return newScheduledThreadPool( 1, threadFactory );
        }

}
