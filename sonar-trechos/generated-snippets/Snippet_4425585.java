public class CoordinatorListener implements ServletContextListener {

    private static final Logger LOG = LoggerFactory.getLogger(CoordinatorListener.class);

    public CoordinatorListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("start coordinator background tasks..");
        Coordinator.startSchedule();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
