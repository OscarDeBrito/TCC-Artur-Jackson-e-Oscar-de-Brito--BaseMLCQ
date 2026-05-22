public static class _BuildAgentUpdate_Flag
        extends Flag
    {
        /**
         * This map lets static methods in this class map strings to the correct
         * instance type at deserialization time.
         */
        private final static Map VALUES_TO_INSTANCES = new HashMap();
        public static final _BuildAgentUpdate_Flag None = new _BuildAgentUpdate_Flag("None");
        public static final _BuildAgentUpdate_Flag Name = new _BuildAgentUpdate_Flag("Name");
        public static final _BuildAgentUpdate_Flag Description = new _BuildAgentUpdate_Flag("Description");
        public static final _BuildAgentUpdate_Flag ControllerUri = new _BuildAgentUpdate_Flag("ControllerUri");
        public static final _BuildAgentUpdate_Flag BuildDirectory = new _BuildAgentUpdate_Flag("BuildDirectory");
        public static final _BuildAgentUpdate_Flag Status = new _BuildAgentUpdate_Flag("Status");
        public static final _BuildAgentUpdate_Flag StatusMessage = new _BuildAgentUpdate_Flag("StatusMessage");
        public static final _BuildAgentUpdate_Flag Tags = new _BuildAgentUpdate_Flag("Tags");
        public static final _BuildAgentUpdate_Flag Enabled = new _BuildAgentUpdate_Flag("Enabled");

        protected _BuildAgentUpdate_Flag(final String name)
        {
            super(name, _BuildAgentUpdate_Flag.VALUES_TO_INSTANCES);
        }
    }
