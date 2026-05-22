public class Snippet__5702663 {

    void prepare(Map<String, Object> topoConf, TopologyContext context, OutputCollector collector,
                     KeyValueState<TaskStream, WindowState> windowState) {
            init(topoConf, context, collector, windowState);
            super.prepare(topoConf, context, collector);
        }

}
