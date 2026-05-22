public abstract class KapuaLocator implements KapuaServiceLoader {

    private static final Logger logger = LoggerFactory.getLogger(KapuaLocator.class);

    private static final KapuaLocator INSTANCE = createInstance();

    /**
     * {@link KapuaLocator} implementation classname specified via "System property" constants
     */
    public static final String LOCATOR_CLASS_NAME_SYSTEM_PROPERTY = "locator.class.impl";

    /**
     * {@link KapuaLocator} implementation classname specified via "Environment property" constants
     */
    public static final String LOCATOR_CLASS_NAME_ENVIRONMENT_PROPERTY = "LOCATOR_CLASS_IMPL";

    // TODO do we need synchronization?

    /**
     * Creates the {@link KapuaLocator} instance,
     *
     * @return
     */
    private static KapuaLocator createInstance() {
        try {
            logger.info("initializing Servicelocator instance... ");
            String locatorImplementation = locatorClassName();
            if (locatorImplementation != null && !locatorImplementation.trim().isEmpty()) {
                try {
                    return (KapuaLocator) Class.forName(locatorImplementation).newInstance();
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    logger.info("An error occurred during Servicelocator initialization", e);
                }
            }

            // proceed with the default service locator instantiation if env variable is null or some error occurred during the specific service locator instantiation

            logger.info("initialize Servicelocator with the default instance... ");
            ServiceLoader<KapuaLocator> serviceLocatorLoaders = ServiceLoader.load(KapuaLocator.class);
            for (KapuaLocator locator : serviceLocatorLoaders) {
                // simply return the first
                logger.info("initialize Servicelocator with the default instance... DONE");
                return locator;
            }
        } catch (Exception e) {
            logger.error("Error initializing locator...", e);
            throw e;
        }
        // none returned

        throw new KapuaRuntimeException(KapuaRuntimeErrorCodes.SERVICE_LOCATOR_UNAVAILABLE);
    }

    /**
     * Return the {@link KapuaLocator} instance (singleton).
     *
     * @return
     */
    public static KapuaLocator getInstance() {
        return INSTANCE;
    }

    /**
     * Get the locator classname implementation looking at the {@link KapuaLocator#LOCATOR_CLASS_NAME_SYSTEM_PROPERTY} system property or falling back to the
     * {@link KapuaLocator#LOCATOR_CLASS_NAME_ENVIRONMENT_PROPERTY} environment variable.
     *
     * @return
     */
    static String locatorClassName() {
        String locatorClass = System.getProperty(LOCATOR_CLASS_NAME_SYSTEM_PROPERTY);
        if (locatorClass != null && !locatorClass.isEmpty()) {
            return locatorClass;
        }

        locatorClass = System.getenv(LOCATOR_CLASS_NAME_ENVIRONMENT_PROPERTY);
        if (locatorClass != null && !locatorClass.isEmpty()) {
            return locatorClass;
        }

        logger.debug("No service locator class resolved. Falling back to default.");
        return null;
    }
}
