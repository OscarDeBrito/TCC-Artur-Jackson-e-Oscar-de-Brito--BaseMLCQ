public class Snippet__7197198 {

    private static void doPaginateWithRecordPaginator(
                final IdentityClient identityClient, final String compartmentId) {

            System.out.println("Pagination Using Record Paginator");
            System.out.println("===================");

            // Build a request object as normal, including any sorting or filtering you wish to do
            final ListUsersRequest request =
                    ListUsersRequest.builder().compartmentId(compartmentId).build();

            // Note that the iterable is over the model object
            final Iterable<User> recordIterator =
                    identityClient.getPaginators().listUsersRecordIterator(request);
            for (User user : recordIterator) {
                System.out.println(user);
            }

            System.out.println();
            System.out.println();
        }

}
