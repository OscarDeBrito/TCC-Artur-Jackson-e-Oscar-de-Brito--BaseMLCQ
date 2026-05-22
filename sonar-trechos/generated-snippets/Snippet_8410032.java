@Generated("org.apache.camel.maven.packaging.SpringBootAutoConfigurationMojo")
@ConfigurationProperties(prefix = "camel.cloud.dns.service-discovery")
public class DnsServiceCallServiceDiscoveryConfigurationProperties
        extends
            DnsServiceCallServiceDiscoveryConfigurationCommon {

    /**
     * Enable the component
     */
    private boolean enabled = true;
    /**
     * Define additional configuration definitions
     */
    private Map<String, DnsServiceCallServiceDiscoveryConfigurationCommon> configurations = new HashMap<>();

    public Map<String, DnsServiceCallServiceDiscoveryConfigurationCommon> getConfigurations() {
        return configurations;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
