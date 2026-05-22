class NamespaceTranslator
{
    private static final Map<String, String> osgiToFelixMap = fillOSGiToFelixMap();
    private static final Map<String, String> felixToOSGiMap = fillFelixToOSGiMap();

    private static Map<String, String> fillOSGiToFelixMap()
    {
        Map<String, String> result = new HashMap<String, String>(4);
        result.put(PackageNamespace.PACKAGE_NAMESPACE, org.apache.felix.bundlerepository.Capability.PACKAGE);
        result.put(ServiceNamespace.SERVICE_NAMESPACE, org.apache.felix.bundlerepository.Capability.SERVICE);
        result.put(BundleNamespace.BUNDLE_NAMESPACE, org.apache.felix.bundlerepository.Capability.BUNDLE);
        result.put(HostNamespace.HOST_NAMESPACE, org.apache.felix.bundlerepository.Capability.FRAGMENT);
        return Collections.unmodifiableMap(result);
    }

    private static Map<String, String> fillFelixToOSGiMap()
    {
        Map<String, String> result = new HashMap<String, String>(4);
        result.put(org.apache.felix.bundlerepository.Capability.PACKAGE, PackageNamespace.PACKAGE_NAMESPACE);
        result.put(org.apache.felix.bundlerepository.Capability.SERVICE, ServiceNamespace.SERVICE_NAMESPACE);
        result.put(org.apache.felix.bundlerepository.Capability.BUNDLE, BundleNamespace.BUNDLE_NAMESPACE);
        result.put(org.apache.felix.bundlerepository.Capability.FRAGMENT, HostNamespace.HOST_NAMESPACE);
        return Collections.unmodifiableMap(result);
    }

    public static String getFelixNamespace(String osgiNamespace)
    {
        String result = osgiToFelixMap.get(osgiNamespace);
        if (result == null)
            return osgiNamespace;
        else
            return result;
    }

    public static Collection<String> getTranslatedFelixNamespaces()
    {
        return felixToOSGiMap.keySet();
    }

    public static String getOSGiNamespace(String felixNamespace)
    {
        String result = felixToOSGiMap.get(felixNamespace);
        if (result == null)
            return felixNamespace;
        else
            return result;
    }

    public static Collection<String> getTranslatedOSGiNamespaces()
    {
        return osgiToFelixMap.keySet();
    }
}
