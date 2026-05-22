private static class WeightedMultiWorkUnit extends MultiWorkUnit implements Comparable<WeightedMultiWorkUnit> {

    private long weight = 0l;

    /**
     * Add a new single workUnit to the current workUnits list. Update the weight by adding the weight of the new workUnit.
     *
     * @param weight the weight of the newWorkUnit.
     * @param newWorkUnit the new work unit.
     */
    private void addWorkUnit(long weight, WorkUnit newWorkUnit) {
      this.addWorkUnit(newWorkUnit);
      this.weight += weight;
    }

    /**
     * Compare with the other weightedMultiWorkUnit based on weight.
     */
    @Override
    public int compareTo(WeightedMultiWorkUnit weightedMultiWorkUnit) {
      return Longs.compare(this.weight, weightedMultiWorkUnit.getWeight());
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int) (this.weight ^ (this.weight >>> 32));
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof WeightedMultiWorkUnit)) {
        return false;
      }
      WeightedMultiWorkUnit weightedMultiWorkUnit = (WeightedMultiWorkUnit) obj;
      return this.weight == weightedMultiWorkUnit.getWeight();
    }

    public long getWeight() {
      return this.weight;
    }
  }
