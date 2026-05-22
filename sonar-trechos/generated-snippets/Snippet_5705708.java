public class Snippet__5705708 {

    public static void logAccess(Integer requestId, InetAddress remoteAddress,
                                     Principal principal, String operation,
                                     String stormName, String accessResult) {
            if (stormName != null && accessResult != null) {
                LOG.info(accessLogBase + " storm-name: {} access result: {}",
                         requestId, remoteAddress, principal, operation, stormName, accessResult);
            } else if (accessResult != null) {
                LOG.info(accessLogBase + " access result: {}",
                         requestId, remoteAddress, principal, operation, accessResult);
            } else {
                // for completeness
                LOG.info(accessLogBase + " storm-name: {}",
                         requestId, remoteAddress, principal, operation, stormName);
            }
        }

}
