public class Snippet__7110627 {

    public ConcurrentMap<String, Double> getMeasurements() {
            if (this.measurements == null) {
                this.measurements = new ConcurrentHashMap<String, Double>();
            }
            return this.measurements;
        }

}
