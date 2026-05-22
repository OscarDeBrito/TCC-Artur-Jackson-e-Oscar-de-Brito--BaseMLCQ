public class TaskManagerDetailsInfo extends TaskManagerInfo {

	public static final String FIELD_NAME_METRICS = "metrics";

	@JsonProperty(FIELD_NAME_METRICS)
	private final TaskManagerMetricsInfo taskManagerMetrics;

	@JsonCreator
	public TaskManagerDetailsInfo(
			@JsonDeserialize(using = ResourceIDDeserializer.class) @JsonProperty(FIELD_NAME_RESOURCE_ID) ResourceID resourceId,
			@JsonProperty(FIELD_NAME_ADDRESS) String address,
			@JsonProperty(FIELD_NAME_DATA_PORT) int dataPort,
			@JsonProperty(FIELD_NAME_LAST_HEARTBEAT) long lastHeartbeat,
			@JsonProperty(FIELD_NAME_NUMBER_SLOTS) int numberSlots,
			@JsonProperty(FIELD_NAME_NUMBER_AVAILABLE_SLOTS) int numberAvailableSlots,
			@JsonProperty(FIELD_NAME_HARDWARE) HardwareDescription hardwareDescription,
			@JsonProperty(FIELD_NAME_METRICS) TaskManagerMetricsInfo taskManagerMetrics) {
		super(
			resourceId,
			address,
			dataPort,
			lastHeartbeat,
			numberSlots,
			numberAvailableSlots,
			hardwareDescription);

		this.taskManagerMetrics = Preconditions.checkNotNull(taskManagerMetrics);
	}

	public TaskManagerDetailsInfo(TaskManagerInfo taskManagerInfo, TaskManagerMetricsInfo taskManagerMetrics) {
		this(
			taskManagerInfo.getResourceId(),
			taskManagerInfo.getAddress(),
			taskManagerInfo.getDataPort(),
			taskManagerInfo.getLastHeartbeat(),
			taskManagerInfo.getNumberSlots(),
			taskManagerInfo.getNumberAvailableSlots(),
			taskManagerInfo.getHardwareDescription(),
			taskManagerMetrics);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		TaskManagerDetailsInfo that = (TaskManagerDetailsInfo) o;
		return Objects.equals(taskManagerMetrics, that.taskManagerMetrics);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), taskManagerMetrics);
	}
}
