class InboundPortsOption implements TemplateOptionCustomizer {
    private static final Logger LOG = LoggerFactory.getLogger(InboundPortsOption.class);

    @Override
    public void apply(TemplateOptions t, ConfigBag props, Object v) {
        int[] inboundPorts = toIntPortArray(v);
        if (LOG.isDebugEnabled())
            LOG.debug("opening inbound ports {} for cloud/type {}", Arrays.toString(inboundPorts), t.getClass());
        t.inboundPorts(inboundPorts);
    }

    private int[] toIntPortArray(Object v) {
        PortRange portRange = PortRanges.fromIterable(Collections.singletonList(v));
        return ArrayUtils.toPrimitive(Iterables.toArray(portRange, Integer.class));
    }
}
