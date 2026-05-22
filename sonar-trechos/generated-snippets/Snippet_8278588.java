public class NetMatcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(NetMatcher.class);

    public static final String NETS_SEPARATOR = ", ";

    /**
     * The DNS Service used to build InetNetworks.
     */
    private final DNSService dnsServer;

    /**
     * The Set of InetNetwork to match against.
     */
    private SortedSet<InetNetwork> networks;

    /**
     * Create a new instance of Netmatcher.
     * 
     * @param nets
     *            a String[] which holds all networks
     * @param dnsServer
     *            the DNSService which will be used in this class
     */
    public NetMatcher(String[] nets, DNSService dnsServer) {
        this.dnsServer = dnsServer;
        initInetNetworks(nets);
    }

    /**
     * Create a new instance of Netmatcher.
     * 
     * @param nets
     *            a Collection which holds all networks
     * @param dnsServer
     *            the DNSService which will be used in this class
     */
    public NetMatcher(Collection<String> nets, DNSService dnsServer) {
        this.dnsServer = dnsServer;
        initInetNetworks(nets);
    }

    public NetMatcher(String commaSeparatedNets, DNSService dnsServer) {
        this.dnsServer = dnsServer;
        List<String> nets = Splitter.on(NETS_SEPARATOR).splitToList(commaSeparatedNets);
        initInetNetworks(nets);
    }

    /**
     * The given String may represent an IP address or a host name.
     * 
     * @param hostIP
     *            the ipAddress or host name to check
     * @see #matchInetNetwork(InetAddress)
     */
    public boolean matchInetNetwork(String hostIP) {

        InetAddress ip;

        try {
            ip = dnsServer.getByName(hostIP);
        } catch (UnknownHostException uhe) {
            LOGGER.info("Cannot resolve address for {}: {}", hostIP, uhe.getMessage());
            return false;
        }

        return matchInetNetwork(ip);

    }

    /**
     * Return true if passed InetAddress match a network which was used to
     * construct the Netmatcher.
     * 
     * @param ip
     *            InetAddress
     * @return true if match the network
     */
    public boolean matchInetNetwork(InetAddress ip) {

        boolean sameNet = false;

        for (Iterator<InetNetwork> iter = networks.iterator(); (!sameNet) && iter.hasNext();) {
            InetNetwork network = iter.next();
            sameNet = network.contains(ip);
        }

        return sameNet;

    }

    @Override
    public String toString() {
        return networks.toString();
    }

    /**
     * Init the class with the given networks.
     * 
     * @param nets
     *            a Collection which holds all networks
     */
    private void initInetNetworks(Collection<String> nets) {
        initInetNetworks(nets.toArray(new String[nets.size()]));
    }

    /**
     * Init the class with the given networks.
     * 
     * @param nets
     *            a String[] which holds all networks
     */
    private void initInetNetworks(String[] nets) {

        networks = new TreeSet<>(Comparator.comparing(Object::toString));

        final InetNetworkBuilder inetNetwork = new InetNetworkBuilder(dnsServer);

        for (String net : nets) {
            try {
                InetNetwork inet = inetNetwork.getFromString(net);
                networks.add(inet);
            } catch (UnknownHostException uhe) {
                LOGGER.info("Cannot resolve address: {}", uhe.getMessage());
            }
        }

    }

}
