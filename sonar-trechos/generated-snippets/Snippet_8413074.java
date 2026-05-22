public class Snippet__8413074 {

    public static String[] filteredFieldNamesOf(final AbstractDescribedSObjectBase object,
            final Predicate<SObjectField> filter) {
            if (object == null) {
                return NONE;
            }

            if (filter == null) {
                return fieldNamesOf(object);
            }

            final SObjectDescription description = object.description();
            final List<SObjectField> fields = description.getFields();

            return fields.stream().filter(filter).map(SObjectField::getName).toArray(String[]::new);
        }

}
