class WindowedProcessorBolt extends BaseWindowedBolt implements StreamBolt {
    private static final Logger LOG = LoggerFactory.getLogger(WindowedProcessorBolt.class);
    private final ProcessorBoltDelegate delegate;
    private final Window<?, ?> window;

    WindowedProcessorBolt(String id, DirectedGraph<Node, Edge> graph,
                          List<ProcessorNode> nodes,
                          Window<?, ?> window) {
        delegate = new ProcessorBoltDelegate(id, graph, nodes);
        this.window = window;
        setWindowConfig();
    }

    @Override
    public void prepare(Map<String, Object> topoConf, TopologyContext context, OutputCollector collector) {
        delegate.prepare(topoConf, context, collector);
    }

    @Override
    public void execute(TupleWindow inputWindow) {
        LOG.trace("Window triggered at {}, inputWindow {}", new Date(), inputWindow);
        if (delegate.isEventTimestamp()) {
            delegate.setEventTimestamp(inputWindow.getEndTimestamp());
        }
        for (Tuple tuple : inputWindow.get()) {
            Pair<Object, String> valueAndStream = delegate.getValueAndStream(tuple);
            if (!StreamUtil.isPunctuation(valueAndStream.getFirst())) {
                delegate.process(valueAndStream.getFirst(), valueAndStream.getSecond());
            }
        }
        for (String stream : delegate.getInitialStreams()) {
            delegate.process(PUNCTUATION, stream);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        delegate.declareOutputFields(declarer);
    }

    @Override
    public void setTimestampField(String fieldName) {
        delegate.setTimestampField(fieldName);
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    private void setWindowConfig() {
        if (window instanceof SlidingWindows) {
            setSlidingWindowParams(window.getWindowLength(), window.getSlidingInterval());
        } else if (window instanceof TumblingWindows) {
            setTumblingWindowParams(window.getWindowLength());
        }
        if (window.getTimestampField() != null) {
            withTimestampField(window.getTimestampField());
        }
        if (window.getLag() != null) {
            withLag(window.getLag());
        }
        if (window.getLateTupleStream() != null) {
            withLateTupleStream(window.getLateTupleStream());
        }
    }

    private void setSlidingWindowParams(Object windowLength, Object slidingInterval) {
        if (windowLength instanceof Count) {
            if (slidingInterval instanceof Count) {
                withWindow((Count) windowLength, (Count) slidingInterval);
            } else if (slidingInterval instanceof Duration) {
                withWindow((Count) windowLength, (Duration) slidingInterval);
            }
        } else if (windowLength instanceof Duration) {
            if (slidingInterval instanceof Count) {
                withWindow((Duration) windowLength, (Count) slidingInterval);
            } else if (slidingInterval instanceof Duration) {
                withWindow((Duration) windowLength, (Duration) slidingInterval);
            }
        }
    }

    private void setTumblingWindowParams(Object windowLength) {
        if (windowLength instanceof Count) {
            withTumblingWindow((Count) windowLength);
        } else if (windowLength instanceof Duration) {
            withTumblingWindow((Duration) windowLength);
        }
    }

    void setStreamToInitialProcessors(Multimap<String, ProcessorNode> streamToInitialProcessors) {
        delegate.setStreamToInitialProcessors(streamToInitialProcessors);
    }
}
