public class Snippet__8163123 {

    @Override
        public SubscriptionInfo lookupSubscription(String clientId, String subscriptionName) throws IOException {
            TransactionContext c = persistenceAdapter.getTransactionContext();
            try {
                return adapter.doGetSubscriberEntry(c, destination, clientId, subscriptionName);
            } catch (SQLException e) {
                JDBCPersistenceAdapter.log("JDBC Failure: ", e);
                throw IOExceptionSupport.create("Failed to lookup subscription for: " + clientId + ". Reason: " + e, e);
            } finally {
                c.close();
            }
        }

}
