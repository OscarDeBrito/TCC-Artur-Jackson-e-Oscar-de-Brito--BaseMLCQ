public class SetMonitorServiceConfigItem extends AbstractConfigItemFacade {

    @Override
    public List<ConfigItem> generateConfig(final NetworkElementCommand cmd) {
        final SetMonitorServiceCommand command = (SetMonitorServiceCommand) cmd;

        final MonitorService monitorService = new MonitorService(command.getConfiguration(), cmd.getAccessDetail(NetworkElementCommand.ROUTER_MONITORING_ENABLE));
        return generateConfigItems(monitorService);
    }

    @Override
    protected List<ConfigItem> generateConfigItems(final ConfigBase configuration) {
        destinationFile = VRScripts.MONITOR_SERVICE_CONFIG;

        return super.generateConfigItems(configuration);
    }
}
