public class Snippet__4613952 {

    public static boolean disjoint(Iterable left, Iterable right) {
            Collection leftCol = asCollection(left);
            Collection rightCol = asCollection(right);

            if (leftCol.isEmpty() || rightCol.isEmpty())
                return true;

            Collection pickFrom = new TreeSet(new NumberAwareComparator());
            pickFrom.addAll(rightCol);

            for (final Object o : leftCol) {
                if (pickFrom.contains(o))
                    return false;
            }
            return true;
        }

}
