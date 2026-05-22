public class FilterComparator implements Comparator<Filter> {

   @Override
   public int compare(Filter o1, Filter o2) {
      assert (o1 != null) && (o2 != null);
      return Integer.compare(o1.getOrdinal(), o2.getOrdinal());
   }

}
