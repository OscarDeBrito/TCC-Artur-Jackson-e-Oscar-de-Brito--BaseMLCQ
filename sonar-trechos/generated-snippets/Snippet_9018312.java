private class ComparableMerger extends FacetSortableMerger {
    Comparable val;
    @Override
    public void merge(Object facetResult, Context mcontext) {
      Comparable other = (Comparable)facetResult;
      if (val == null) {
        val = other;
      } else {
        if ( other.compareTo(val) * minmax < 0 ) {
          val = other;
        }
      }
    }

    @Override
    public Object getMergedResult() {
      return val;
    }

    @Override
    public int compareTo(FacetSortableMerger other, FacetRequest.SortDirection direction) {
      // NOTE: we don't use the minmax multiplier here because we still want natural ordering between slots (i.e. min(field) asc and max(field) asc) both sort "A" before "Z")
      return this.val.compareTo(((ComparableMerger)other).val);
    }
  }
