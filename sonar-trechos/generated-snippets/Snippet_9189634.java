private class FunctionProvider
    {

        private IAggrFunction[] funcs;
        private HashMap<String, IAggrFunction> displayName2Funcs;
        private HashMap<String, IAggrFunction> name2Funcs;

        public FunctionProvider( IAggrFunction[] funcs )
        {
            this.funcs = funcs == null ? new IAggrFunction[0] : funcs;
            this.displayName2Funcs = new HashMap<String, IAggrFunction>( funcs.length );
            this.name2Funcs = new HashMap<String, IAggrFunction>( funcs.length );
            for ( IAggrFunction func : funcs )
            {
                displayName2Funcs.put( func.getDisplayName( ), func );
                name2Funcs.put( func.getName( ), func );
            }
        }

        public IAggrFunction getFunction( String funcName )
        {
            return name2Funcs.get( funcName );
        }

        public IAggrFunction getFunctionByDisplayText( String displayText )
        {
            return displayName2Funcs.get( displayText );
        }

        public String[] getDisplayTexts( )
        {
            String[] displayTexts = new String[funcs.length];
            for ( int i = 0; i < funcs.length; i++ )
            {
                displayTexts[i] = funcs[i].getDisplayName( );
            }
            Arrays.sort( displayTexts, new AlphabeticallyComparator( ) );
            return displayTexts;
        }
    }
