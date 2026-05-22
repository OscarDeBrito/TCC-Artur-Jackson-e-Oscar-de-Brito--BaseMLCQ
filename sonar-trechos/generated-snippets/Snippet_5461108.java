@Slf4j
@Data
public class Offloaders implements AutoCloseable {

    private final List<Pair<NarClassLoader, LedgerOffloaderFactory>> offloaders = new ArrayList<>();

    public LedgerOffloaderFactory getOffloaderFactory(String driverName) throws IOException {
        for (Pair<NarClassLoader, LedgerOffloaderFactory> factory : offloaders) {
            if (factory.getRight().isDriverSupported(driverName)) {
                return factory.getRight();
            }
        }
        throw new IOException("No offloader found for driver '" + driverName + "'." +
            " Please make sure you dropped the offloader nar packages under `${PULSAR_HOME}/offloaders`.");
    }

    @Override
    public void close() throws Exception {
        offloaders.forEach(offloader -> {
            try {
                offloader.getLeft().close();
            } catch (IOException e) {
                log.warn("Failed to close nar class loader for offloader '{}': {}",
                    offloader.getRight().getClass(), e.getMessage());
            }
        });
    }
}
