public class Snippet__6251730 {

    public static void assertAssignmentQuota(final Long parentId, final long requested, final long limit,
                @NotNull final Class<?> type, @NotNull final Class<?> parentType, final Function<Long, Long> countFct) {
            assertAssignmentQuota(parentId, requested, limit, type.getSimpleName(), parentType.getSimpleName(), countFct);
        }

}
