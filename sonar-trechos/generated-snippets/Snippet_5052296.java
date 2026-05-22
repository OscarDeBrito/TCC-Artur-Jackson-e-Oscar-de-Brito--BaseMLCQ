public static class Builder<B extends Builder<B>> extends AbstractAppender.Builder<B>
        implements org.apache.logging.log4j.core.util.Builder<CassandraAppender> {

        /**
         * List of Cassandra node contact points. Addresses without a port (or port set to 0) will use the default
         * Cassandra port (9042).
         */
        @PluginElement("ContactPoints")
        @Required(message = "No Cassandra servers provided")
        private SocketAddress[] contactPoints = new SocketAddress[]{SocketAddress.getLoopback()};

        /**
         * List of column mappings to convert a LogEvent into a database row.
         */
        @PluginElement("Columns")
        @Required(message = "No Cassandra columns provided")
        private ColumnMapping[] columns;

        @PluginBuilderAttribute
        private boolean useTls;

        @PluginBuilderAttribute
        @Required(message = "No cluster name provided")
        private String clusterName;

        @PluginBuilderAttribute
        @Required(message = "No keyspace provided")
        private String keyspace;

        @PluginBuilderAttribute
        @Required(message = "No table name provided")
        private String table;

        @PluginBuilderAttribute
        private String username;

        @PluginBuilderAttribute(sensitive = true)
        private String password;

        /**
         * Override the default TimestampGenerator with one based on the configured {@link Clock}.
         */
        @PluginBuilderAttribute
        private boolean useClockForTimestampGenerator;

        /**
         * Number of LogEvents to buffer before writing. Can be used with or without batch statements.
         */
        @PluginBuilderAttribute
        private int bufferSize;

        /**
         * Whether or not to use batch statements when inserting records.
         */
        @PluginBuilderAttribute
        private boolean batched;

        /**
         * If batch statements are enabled, use this type of batch statement.
         */
        @PluginBuilderAttribute
        private BatchStatement.Type batchType = BatchStatement.Type.LOGGED;

        public B setContactPoints(final SocketAddress... contactPoints) {
            this.contactPoints = contactPoints;
            return asBuilder();
        }

        public B setColumns(final ColumnMapping... columns) {
            this.columns = columns;
            return asBuilder();
        }

        public B setUseTls(final boolean useTls) {
            this.useTls = useTls;
            return asBuilder();
        }

        public B setClusterName(final String clusterName) {
            this.clusterName = clusterName;
            return asBuilder();
        }

        public B setKeyspace(final String keyspace) {
            this.keyspace = keyspace;
            return asBuilder();
        }

        public B setTable(final String table) {
            this.table = table;
            return asBuilder();
        }

        public B setUsername(final String username) {
            this.username = username;
            return asBuilder();
        }

        public B setPassword(final String password) {
            this.password = password;
            return asBuilder();
        }

        public B setUseClockForTimestampGenerator(final boolean useClockForTimestampGenerator) {
            this.useClockForTimestampGenerator = useClockForTimestampGenerator;
            return asBuilder();
        }

        public B setBufferSize(final int bufferSize) {
            this.bufferSize = bufferSize;
            return asBuilder();
        }

        public B setBatched(final boolean batched) {
            this.batched = batched;
            return asBuilder();
        }

        public B setBatchType(final BatchStatement.Type batchType) {
            this.batchType = batchType;
            return asBuilder();
        }

        @Override
        public CassandraAppender build() {
            final CassandraManager manager = CassandraManager.getManager(getName(), contactPoints, columns, useTls,
                clusterName, keyspace, table, username, password, useClockForTimestampGenerator, bufferSize, batched,
                batchType);
            return new CassandraAppender(getName(), getFilter(), isIgnoreExceptions(), getPropertyArray(), manager);
        }

    }
