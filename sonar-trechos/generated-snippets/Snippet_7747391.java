public class Snippet__7747391 {

    @Override
        public void onManagementStarting() {
            super.onManagementStarting();
        
            exchange = (getConfig(EXCHANGE_NAME) != null) ? getConfig(EXCHANGE_NAME) : getDefaultExchangeName();
            virtualHost = getConfig(RabbitBroker.VIRTUAL_HOST_NAME);
            sensors().set(RabbitBroker.VIRTUAL_HOST_NAME, virtualHost);
        
            machine = (SshMachineLocation) Iterables.find(getParent().getLocations(), Predicates.instanceOf(SshMachineLocation.class));
            shellEnvironment = getParent().getShellEnvironment();
        }

}
