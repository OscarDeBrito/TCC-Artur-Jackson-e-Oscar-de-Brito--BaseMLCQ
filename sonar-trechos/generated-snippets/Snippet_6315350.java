public class Snippet__6315350 {

    protected void modified(final Map<String, Object> properties) throws Exception {
            logger.debug("Updating properties: {}", properties);
            try {
                if (isRestartNeeded(properties)) {
                    logger.info("Need restart");
                    stop();
                    start(properties);
                }

                // apply current routes

                applyRoutes(properties);
            } catch (Exception e) {
                logger.warn("Problem updating component", e);
                throw e;
            }
        }

}
