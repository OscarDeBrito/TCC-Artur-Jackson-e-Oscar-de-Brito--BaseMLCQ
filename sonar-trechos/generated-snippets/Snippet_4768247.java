public static class Builder extends BaseServer.Builder<MetadataServer> {
        /**
         * @return a {@link MetadataServer} object.
         */
        public MetadataServer build()  {
            validate();
            return new MetadataServer(getOpts());
        }
    }
