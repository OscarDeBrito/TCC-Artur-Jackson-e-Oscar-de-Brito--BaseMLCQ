public class Snippet__6328482 {

    private static final class LogError implements LoggerCall {
            @Override
            public void log(final Logger logger, final String msg, final Throwable t) {
                logger.error(msg, t);
            }
            @Override
            public void log(final Logger logger, final String message) {
                logger.error(message);
            }
        }

}
