public class SavepointStatusMessageParameters extends MessageParameters {

	public final JobIDPathParameter jobIdPathParameter = new JobIDPathParameter();

	public final TriggerIdPathParameter triggerIdPathParameter = new TriggerIdPathParameter();

	@Override
	public Collection<MessagePathParameter<?>> getPathParameters() {
		return Collections.unmodifiableCollection(
			Arrays.asList(jobIdPathParameter, triggerIdPathParameter));
	}

	@Override
	public Collection<MessageQueryParameter<?>> getQueryParameters() {
		return Collections.emptyList();
	}
}
