public class DefaultStatisticsRepository implements StatisticsRepository {

	private ConcurrentMap<String, MutableRetryStatistics> map = new ConcurrentHashMap<String, MutableRetryStatistics>();

	private RetryStatisticsFactory factory = new DefaultRetryStatisticsFactory();

	public void setRetryStatisticsFactory(RetryStatisticsFactory factory) {
		this.factory = factory;
	}

	@Override
	public RetryStatistics findOne(String name) {
		return map.get(name);
	}

	@Override
	public Iterable<RetryStatistics> findAll() {
		return new ArrayList<RetryStatistics>(map.values());
	}

	@Override
	public void addStarted(String name) {
		getStatistics(name).incrementStartedCount();
	}

	@Override
	public void addError(String name) {
		getStatistics(name).incrementErrorCount();
	}

	@Override
	public void addRecovery(String name) {
		getStatistics(name).incrementRecoveryCount();
	}

	@Override
	public void addComplete(String name) {
		getStatistics(name).incrementCompleteCount();
	}

	@Override
	public void addAbort(String name) {
		getStatistics(name).incrementAbortCount();
	}

	private MutableRetryStatistics getStatistics(String name) {
		MutableRetryStatistics stats;
		if (!map.containsKey(name)) {
			map.putIfAbsent(name, factory.create(name));
		}
		stats = map.get(name);
		return stats;
	}

}
