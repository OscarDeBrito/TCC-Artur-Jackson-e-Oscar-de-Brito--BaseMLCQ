public class WeightedRunningAverage implements RunningAverage, Serializable {

  private double totalWeight;
  private double average;

  public WeightedRunningAverage() {
    totalWeight = 0.0;
    average = Double.NaN;
  }

  @Override
  public synchronized void addDatum(double datum) {
    addDatum(datum, 1.0);
  }

  public synchronized void addDatum(double datum, double weight) {
    double oldTotalWeight = totalWeight;
    totalWeight += weight;
    if (oldTotalWeight <= 0.0) {
      average = datum;
    } else {
      average = average * oldTotalWeight / totalWeight + datum * weight / totalWeight;
    }
  }

  @Override
  public synchronized void removeDatum(double datum) {
    removeDatum(datum, 1.0);
  }

  public synchronized void removeDatum(double datum, double weight) {
    double oldTotalWeight = totalWeight;
    totalWeight -= weight;
    if (totalWeight <= 0.0) {
      average = Double.NaN;
      totalWeight = 0.0;
    } else {
      average = average * oldTotalWeight / totalWeight - datum * weight / totalWeight;
    }
  }

  @Override
  public synchronized void changeDatum(double delta) {
    changeDatum(delta, 1.0);
  }

  public synchronized void changeDatum(double delta, double weight) {
    Preconditions.checkArgument(weight <= totalWeight, "weight must be <= totalWeight");
    average += delta * weight / totalWeight;
  }

  public synchronized double getTotalWeight() {
    return totalWeight;
  }

  /** @return {@link #getTotalWeight()} */
  @Override
  public synchronized int getCount() {
    return (int) totalWeight;
  }

  @Override
  public synchronized double getAverage() {
    return average;
  }

  @Override
  public RunningAverage inverse() {
    return new InvertedRunningAverage(this);
  }

  @Override
  public synchronized String toString() {
    return String.valueOf(average);
  }

}
