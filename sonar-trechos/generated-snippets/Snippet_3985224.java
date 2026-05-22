public static class DeleteBySearchProcessor extends AbstractProcessor<DeleteBySearch>
    {
        public DeleteBySearchProcessor()
        {
            this( DeleteBySearch.class );
        }

        public DeleteBySearchProcessor( Class<? extends DeleteBySearch> realType )
        {
            super( realType );
        }

        @Override
        protected void doProcess( SQLProcessorAggregator processor, DeleteBySearch object,
                                  StringBuilder builder )
        {
            builder.append( "DELETE FROM" ).append( SQLConstants.TOKEN_SEPARATOR );
            processor.process( object.getTargetTable(), builder );
            QueryProcessing.processOptionalBooleanExpression( processor, builder,
                                                              object.getWhere(),
                                                              SQLConstants.NEWLINE, SQLConstants.WHERE );
        }
    }
