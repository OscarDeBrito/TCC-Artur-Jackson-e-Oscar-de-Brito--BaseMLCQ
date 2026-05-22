public class Snippet__5832184 {

    @Override
        public PreparedStatement prepareStatement(final String sql, final int resultSetType, final int resultSetConcurrency)
                throws SQLException {
            if (null == pstmtPool) {
                throw new SQLException("Statement pool is null - closed or invalid PoolingConnection.");
            }
            try {
                return pstmtPool.borrowObject(createKey(sql, resultSetType, resultSetConcurrency));
            } catch (final NoSuchElementException e) {
                throw new SQLException("MaxOpenPreparedStatements limit reached", e);
            } catch (final RuntimeException e) {
                throw e;
            } catch (final Exception e) {
                throw new SQLException("Borrow prepareStatement from pool failed", e);
            }
        }

}
