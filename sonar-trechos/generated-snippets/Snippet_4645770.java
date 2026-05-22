public class CompleteGraph
extends GeneratedGraph {

	private LongParameter vertexCount = new LongParameter(this, "vertex_count")
		.setMinimumValue(MINIMUM_VERTEX_COUNT);

	@Override
	public String getIdentity() {
		return getName() + " (" + vertexCount.getValue() + ")";
	}

	@Override
	protected long vertexCount() {
		return vertexCount.getValue();
	}

	@Override
	public Graph<LongValue, NullValue, NullValue> create(ExecutionEnvironment env) throws Exception {
		return new org.apache.flink.graph.generator.CompleteGraph(env, vertexCount.getValue())
			.setParallelism(parallelism.getValue().intValue())
			.generate();
	}
}
