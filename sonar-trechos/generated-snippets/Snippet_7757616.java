public class SpatialDimFilter implements DimFilter
{
  private final String dimension;
  private final Bound bound;

  @JsonCreator
  public SpatialDimFilter(
      @JsonProperty("dimension") String dimension,
      @JsonProperty("bound") Bound bound
  )
  {
    Preconditions.checkArgument(dimension != null, "dimension must not be null");
    Preconditions.checkArgument(bound != null, "bound must not be null");

    this.dimension = dimension;
    this.bound = bound;
  }

  @Override
  public byte[] getCacheKey()
  {
    byte[] dimBytes = StringUtils.toUtf8(dimension);
    byte[] boundBytes = bound.getCacheKey();

    return ByteBuffer.allocate(2 + dimBytes.length + boundBytes.length)
                     .put(DimFilterUtils.SPATIAL_CACHE_ID)
                     .put(dimBytes)
                     .put(DimFilterUtils.STRING_SEPARATOR)
                     .put(boundBytes)
                     .array();
  }

  @Override
  public DimFilter optimize()
  {
    return this;
  }

  @JsonProperty
  public String getDimension()
  {
    return dimension;
  }

  @JsonProperty
  public Bound getBound()
  {
    return bound;
  }

  @Override
  public Filter toFilter()
  {
    return new SpatialFilter(dimension, bound);
  }

  @Override
  public RangeSet<String> getDimensionRangeSet(String dimension)
  {
    return null;
  }

  @Override
  public HashSet<String> getRequiredColumns()
  {
    return Sets.newHashSet(dimension);
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SpatialDimFilter that = (SpatialDimFilter) o;

    if (bound != null ? !bound.equals(that.bound) : that.bound != null) {
      return false;
    }
    if (dimension != null ? !dimension.equals(that.dimension) : that.dimension != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode()
  {
    int result = dimension != null ? dimension.hashCode() : 0;
    result = 31 * result + (bound != null ? bound.hashCode() : 0);
    return result;
  }

  @Override
  public String toString()
  {
    return "SpatialDimFilter{" +
           "dimension='" + dimension + '\'' +
           ", bound=" + bound +
           '}';
  }
}
