public class Snippet__4122935 {

    @Override
        protected Optional<HostAndPort> getMappedEndpoint(Entity source, MachineLocation machine, int port) {
            String address = source.sensors().get(Attributes.SUBNET_ADDRESS);
            if (Strings.isNonBlank(address)) {
                return Optional.of(HostAndPort.fromParts(address, port));
            } else {
                return Optional.absent();
            }
        }

}
