public class CountTrigger implements Trigger {

	private final int count;
	private final long period;
	private final TimeUnit timeUnit;
	private volatile long initialDelay = 0;
	private volatile boolean fixedRate = false;
	private volatile int counter = 0;

	/**
	 * Create a trigger with the given period in milliseconds and firing
	 * exactly one time.
	 *
	 * @param period the period
	 */
	public CountTrigger(long period) {
		this(1, period, null);
	}

	/**
	 * Create a trigger with the given count, period and time unit. The time unit will
	 * apply not only to the period but also to any 'initialDelay' value, if
	 * configured on this Trigger later via {@link #setInitialDelay(long)}.
	 *
	 * @param count the count
	 * @param period the period
	 * @param timeUnit the time unit
	 */
	public CountTrigger(int count, long period, TimeUnit timeUnit) {
		this(count, period, 0, timeUnit);
	}

	/**
	 * Create a trigger with the given count, period and time unit. The time unit will
	 * apply not only to the period but also to any 'initialDelay' value, if
	 * configured on this Trigger later via {@link #setInitialDelay(long)}.
	 *
	 * @param count the count
	 * @param period the period
	 * @param timeUnit the time unit
	 * @param initialDelay the initial delay
	 */
	public CountTrigger(int count, long period, long initialDelay, TimeUnit timeUnit) {
		Assert.isTrue(period >= 0, "period must not be negative");
		Assert.isTrue(count >= 0, "count must not be negative");
		this.timeUnit = (timeUnit != null ? timeUnit : TimeUnit.MILLISECONDS);
		this.period = this.timeUnit.toMillis(period);
		this.count = count;
		setInitialDelay(initialDelay);
	}

	/**
	 * Specify the delay for the initial execution. It will be evaluated in
	 * terms of this trigger's {@link TimeUnit}. If no time unit was explicitly
	 * provided upon instantiation, the default is milliseconds.
	 *
	 * @param initialDelay the new initial delay
	 */
	public void setInitialDelay(long initialDelay) {
		this.initialDelay = this.timeUnit.toMillis(initialDelay);
	}

	/**
	 * Specify whether the periodic interval should be measured between the
	 * scheduled start times rather than between actual completion times.
	 * The latter, "fixed delay" behavior, is the default.
	 *
	 * @param fixedRate the new fixed rate
	 */
	public void setFixedRate(boolean fixedRate) {
		this.fixedRate = fixedRate;
	}

	@Override
	public Date nextExecutionTime(TriggerContext triggerContext) {
		if (count > 0) {
			if (++counter > count) {
				return null;
			}
		}
		if (triggerContext.lastScheduledExecutionTime() == null) {
			return new Date(System.currentTimeMillis() + this.initialDelay);
		}
		else if (this.fixedRate) {
			return new Date(triggerContext.lastScheduledExecutionTime().getTime() + this.period);
		}
		return new Date(triggerContext.lastCompletionTime().getTime() + this.period);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + (fixedRate ? 1231 : 1237);
		result = prime * result + (int) (initialDelay ^ (initialDelay >>> 32));
		result = prime * result + (int) (period ^ (period >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountTrigger other = (CountTrigger) obj;
		if (count != other.count)
			return false;
		if (fixedRate != other.fixedRate)
			return false;
		if (initialDelay != other.initialDelay)
			return false;
		if (period != other.period)
			return false;
		return true;
	}
}
