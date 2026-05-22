public interface CanalMetricsService {

    /**
     * Initialization on canal server startup.
     */
    void initialize();

    /**
     * Clean-up at canal server stop phase.
     */
    void terminate();

    /**
     * @return {@code true} if the metrics service is running, otherwise {@code false}.
     */
    boolean isRunning();

    /**
     * Register instance level metrics for specified instance.
     * @param instance {@link CanalInstance}
     */
    void register(CanalInstance instance);

    /**
     * Unregister instance level metrics for specified instance.
     * @param instance {@link CanalInstance}
     */
    void unregister(CanalInstance instance);

    /**
     * @param port server port for pull
     */
    void setServerPort(int port);

}
