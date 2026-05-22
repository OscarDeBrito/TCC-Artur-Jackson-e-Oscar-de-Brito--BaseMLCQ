public class Miniball {

  private int dimension;
  private com.dreizak.miniball.highdim.Miniball mb;
  private PointStorage pointSet;

  public Miniball(int dimension) {
    this.dimension = dimension;
  }

  void clear() {
    this.pointSet = new PointStorage(this.dimension);
  }

  void check_in(double[] array) {
    this.pointSet.add(array);
  }

  double[] center() {
    return this.mb.center();
  }

  double radius() {
    return this.mb.radius();
  }

  void build() {
    this.mb = new com.dreizak.miniball.highdim.Miniball(this.pointSet);
  }

  public class PointStorage implements PointSet {

    protected int dimension;
    protected List<double[]> L;

    public PointStorage(int dimension) {
      this.dimension = dimension;
      this.L = new ArrayList<double[]>();
    }

    public void add(double[] array) {
      this.L.add(array);
    }

    public int size() {
      return L.size();
    }

    public int dimension() {
      return dimension;
    }

    public double coord(int point, int coordinate) {
      return L.get(point)[coordinate];
    }
  }
}
