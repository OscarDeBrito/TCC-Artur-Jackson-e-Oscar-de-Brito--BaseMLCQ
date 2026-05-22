public class Snippet__4308354 {

    public static final class Builder extends SCBuilder<BitStringSyntaxChecker>
        {
            /**
             * The Builder constructor
             */
            private Builder()
            {
                super( SchemaConstants.BIT_STRING_SYNTAX );
            }
        
        
            /**
             * Create a new instance of BitStringSyntaxChecker
             * @return A new instance of BitStringSyntaxChecker
             */
            @Override
            public BitStringSyntaxChecker build()
            {
                return new BitStringSyntaxChecker( oid );
            }
        }

}
