public interface ICCApplication extends IApplication {

    IJobCapacityController getJobCapacityController();

    IConfigManager getConfigManager();

    IGatekeeper getGatekeeper();

}
