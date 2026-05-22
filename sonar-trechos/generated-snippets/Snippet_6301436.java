public class OpenJDK8ServerALPNProcessor implements ALPNProcessor.Server
{
    private static final Logger LOG = Log.getLogger(OpenJDK8ServerALPNProcessor.class);
    
    @Override
    public void init()
    {
        if (JavaVersion.VERSION.getPlatform()!=8)
            throw new IllegalStateException(this + " not applicable for java "+JavaVersion.VERSION);
        if (ALPN.class.getClassLoader()!=null)
            throw new IllegalStateException(ALPN.class.getName() + " must be on JVM boot classpath");
        if (LOG.isDebugEnabled())
            ALPN.debug = true;
    }

    @Override
    public boolean appliesTo(SSLEngine sslEngine)
    {
        return sslEngine.getClass().getName().startsWith("sun.security.ssl.");
    }

    @Override
    public void configure(SSLEngine sslEngine, Connection connection)
    {
        connection.addListener(new ALPNListener((ALPNServerConnection)connection));
    }

    private final class ALPNListener implements ALPN.ServerProvider, Connection.Listener
    {
        private final ALPNServerConnection alpnConnection;

        private ALPNListener(ALPNServerConnection connection)
        {
            alpnConnection = connection;
        }

        @Override
        public void onOpened(Connection connection)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("onOpened {}", alpnConnection);
            ALPN.put(alpnConnection.getSSLEngine(), this);
        }

        @Override
        public void onClosed(Connection connection)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("onClosed {}", alpnConnection);
            ALPN.remove(alpnConnection.getSSLEngine());
        }
        
        @Override
        public void unsupported()
        {
            if (LOG.isDebugEnabled())
                LOG.debug("unsupported {}", alpnConnection);
            alpnConnection.select(Collections.emptyList());
        }

        @Override
        public String select(List<String> protocols) throws SSLException
        {
            if (LOG.isDebugEnabled())
                LOG.debug("select {} {}", alpnConnection, protocols);
            alpnConnection.select(protocols);
            return alpnConnection.getProtocol();
        }
    }
}
