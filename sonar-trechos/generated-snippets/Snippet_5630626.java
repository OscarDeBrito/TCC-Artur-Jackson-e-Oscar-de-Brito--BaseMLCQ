public static class MissingRequirement extends ConfigurationException
    {
        private static final long serialVersionUID = -5579697104441150933L;
        
        public MissingRequirement( String required, String var, String source, int line )
        {
            super( null, source, line );
            this.required = required;
        }
        public String required;
    }
