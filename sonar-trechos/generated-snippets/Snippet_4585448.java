public interface ClassDiscoveryFilter {

    public boolean rangeDiscoveryRequired(DiscoveryRange discoveryRange);

    public boolean jarFileDiscoveryRequired(String url);

    public boolean directoryDiscoveryRequired(String url);

    public boolean packageDiscoveryRequired(String packageName);
}
