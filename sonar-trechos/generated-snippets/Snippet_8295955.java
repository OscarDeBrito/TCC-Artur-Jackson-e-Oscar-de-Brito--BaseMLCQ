public class ManageSieveServer extends AbstractConfigurableAsyncServer implements ManageSieveServerMBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManageSieveServer.class);

    static final String SSL_HANDLER = "sslHandler";
    static final String FRAMER = "framer";
    static final String CORE_HANDLER = "coreHandler";
    static final String GROUP_HANDLER = "groupHandler";
    static final String CONNECTION_LIMIT_HANDLER = "connectionLimitHandler";
    static final String CONNECTION_LIMIT_PER_IP_HANDLER = "connectionPerIpLimitHandler";
    static final String CONNECTION_COUNT_HANDLER = "connectionCountHandler";
    static final String CHUNK_WRITE_HANDLER = "chunkWriteHandler";
    static final String EXECUTION_HANDLER = "executionHandler";

    private final int maxLineLength;
    private final ManageSieveProcessor manageSieveProcessor;

    public ManageSieveServer(int maxLineLength, ManageSieveProcessor manageSieveProcessor) {
        this.maxLineLength = maxLineLength;
        this.manageSieveProcessor = manageSieveProcessor;
    }

    @Override
    protected int getDefaultPort() {
        return 4190;
    }

    @Override
    protected String getDefaultJMXName() {
        return "managesieveserver";
    }

    @Override
    protected ChannelUpstreamHandler createCoreHandler() {
        return new ManageSieveChannelUpstreamHandler(manageSieveProcessor,
            getEncryption() == null ? null : getEncryption().getContext(),
            getEnabledCipherSuites(),
            isSSL(),
            LOGGER);
    }

    private boolean isSSL() {
        return getEncryption() != null
            && !getEncryption().isStartTLS();
    }

    @Override
    protected ChannelPipelineFactory createPipelineFactory(final ChannelGroup group) {

        return new ChannelPipelineFactory() {

            private final ChannelGroupHandler groupHandler = new ChannelGroupHandler(group);

            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = pipeline();
                Encryption secure = getEncryption();
                if (secure != null && !secure.isStartTLS()) {
                    // We need to set clientMode to false.
                    // See https://issues.apache.org/jira/browse/JAMES-1025
                    SSLEngine engine = secure.getContext().createSSLEngine();
                    engine.setUseClientMode(false);
                    pipeline.addFirst(SSL_HANDLER, new SslHandler(engine));

                }
                pipeline.addLast(GROUP_HANDLER, groupHandler);
                pipeline.addLast(CONNECTION_LIMIT_HANDLER, new ConnectionLimitUpstreamHandler(ManageSieveServer.this.connectionLimit));
                pipeline.addLast(CONNECTION_LIMIT_PER_IP_HANDLER, new ConnectionPerIpLimitUpstreamHandler(ManageSieveServer.this.connPerIP));
                // Add the text line decoder which limit the max line length,
                // don't strip the delimiter and use CRLF as delimiter
                // Use a SwitchableDelimiterBasedFrameDecoder, see JAMES-1436
                pipeline.addLast(FRAMER, getFrameHandlerFactory().create(pipeline));
                pipeline.addLast(CONNECTION_COUNT_HANDLER, getConnectionCountHandler());
                pipeline.addLast(CHUNK_WRITE_HANDLER, new ChunkedWriteHandler());

                ExecutionHandler ehandler = getExecutionHandler();
                if (ehandler  != null) {
                    pipeline.addLast(EXECUTION_HANDLER, ehandler);

                }
                pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast(CORE_HANDLER, createCoreHandler());
                pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
                return pipeline;
            }

        };
    }

    @Override
    public String getServiceType() {
        return "Manage Sieve Service";
    }

    @Override
    protected ChannelHandlerFactory createFrameHandlerFactory() {
        return new LineDelimiterBasedChannelHandlerFactory(maxLineLength);
    }
}
