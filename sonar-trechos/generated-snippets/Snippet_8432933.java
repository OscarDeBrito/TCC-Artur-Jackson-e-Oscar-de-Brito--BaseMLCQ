public class Snippet__8432933 {

    public Snippet__8432933(Long timeout, List<ServiceDiscovery> serviceDiscoveryList) {
            // Created a chained service discovery that collects services from multiple
            // ServiceDiscovery
            this.delegate = new CombinedServiceDiscovery(serviceDiscoveryList);

            // If a timeout is provided, wrap the serviceDiscovery with a caching
            // strategy so the discovery implementations are not queried for each
            // discovery request
            if (timeout != null && timeout > 0) {
                this.delegate = CachingServiceDiscovery.wrap(this.delegate, timeout, TimeUnit.MILLISECONDS);
            }
        }

}
