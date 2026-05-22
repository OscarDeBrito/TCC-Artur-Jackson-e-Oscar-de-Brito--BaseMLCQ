private static class IntervalYearMonthAccessor extends IntAccessor {
    private final TimeUnitRange range;

    private IntervalYearMonthAccessor(Getter getter, TimeUnitRange range) {
      super(getter);
      this.range = range;
    }

    @Override public String getString() throws SQLException {
      final int v = getInt();
      if (v == 0 && wasNull()) {
        return null;
      }
      return DateTimeUtils.intervalYearMonthToString(v, range);
    }
  }
