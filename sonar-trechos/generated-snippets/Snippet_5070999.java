public class Snippet__5070999 {

    @Override
            public void run() {
                while (!shutdown) {
                    try {
                        sleep(factoryData.reconnectIntervalMillis);
                        reconnect();
                    } catch (final InterruptedException | SQLException e) {
                        logger().debug("Cannot reestablish JDBC connection to {}: {}", factoryData, e.getLocalizedMessage(),
                                e);
                    } finally {
                        latch.countDown();
                    }
                }
            }

}
