public class SortedByFacetOnCollectionFromLayoutProperties extends SortedByFacetAbstract {

    public static SortedByFacet create(Properties properties, FacetHolder holder) {
        final Class sortedBy = sortedBy(properties);
        return sortedBy != null? new SortedByFacetOnCollectionFromLayoutProperties(sortedBy, holder): null;
    }

    private SortedByFacetOnCollectionFromLayoutProperties(Class<? extends Comparator<?>> sortedBy, FacetHolder holder) {
        super(sortedBy, holder);
    }

    private static Class<?> sortedBy(Properties properties) {
        if(properties == null) {
            return null;
        }
        String sortedBy = Strings.emptyToNull(properties.getProperty("sortedBy"));
        if (sortedBy == null) {
            return null;
        }
        final Class<?> sortedByClass = ClassUtil.forName(sortedBy);
        if(sortedByClass == Comparator.class) {
            return null;
        }
        return sortedByClass;
    }

}
