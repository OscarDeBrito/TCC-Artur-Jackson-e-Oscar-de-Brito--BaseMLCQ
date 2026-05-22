public interface XAConnectionFactory extends ConnectionFactory {
    /**
     * Gets the TransactionRegistry for this connection factory which contains a the XAResource for every connection
     * created by this factory.
     *
     * @return the transaction registry for this connection factory
     */
    TransactionRegistry getTransactionRegistry();

    /**
     * Create a new {@link java.sql.Connection} in an implementation specific fashion.
     * <p>
     * An implementation can assume that the caller of this will wrap the connection in a proxy that protects access to
     * the setAutoCommit, commit and rollback when enrolled in a XA transaction.
     * </p>
     *
     * @return a new {@link java.sql.Connection}
     * @throws java.sql.SQLException
     *             if a database error occurs creating the connection
     */
    @Override
    Connection createConnection() throws SQLException;
}
