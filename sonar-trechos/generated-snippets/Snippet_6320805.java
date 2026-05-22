public class DeviceTabPackagesDescriptor extends AbstractEntityTabDescriptor<GwtDevice, DeviceTabPackages, DeviceView> {

    @Override
    public DeviceTabPackages getTabViewInstance(DeviceView view, GwtSession currentSession) {
        return new DeviceTabPackages(currentSession, view);
    }

    @Override
    public String getViewId() {
        return "device.packages";
    }

    @Override
    public Integer getOrder() {
        return 300;
    }

    @Override
    public Boolean isEnabled(GwtSession currentSession) {
        return currentSession.hasPermission(DeviceManagementSessionPermission.read());
    }
}
