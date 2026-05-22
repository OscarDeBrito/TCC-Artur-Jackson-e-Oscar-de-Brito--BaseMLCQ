private static class Function_FirstDayOfFiscalQuarter extends Function_temp
	{

		private static final long serialVersionUID = 1L;

		Function_FirstDayOfFiscalQuarter( )
		{
			minParamCount = 1;
			maxParamCount = 2;
		}

		protected Object getValue( Object[] args, IScriptFunctionContext context ) throws BirtException
		{
			if ( existNullValue( args ) )
			{
				return null;
			}
			Calendar current;
			if ( args[0] instanceof Number )
			{
				current = getFiscalYearStateDate( context, args );
				// Quarter starts with 1
				current.add( Calendar.MONTH,
						( ( (Number) args[0] ).intValue( ) - 1 ) * 3 );
			}
			else
			{
				current = getCalendar( DataTypeUtil.toDate( args[0] ) );
				Calendar start = getFiscalYearStateDate( context, args );
				adjustFiscalMonth( current, start );
				int monthRemaindary = ( current.get( Calendar.MONTH )
						- start.get( Calendar.MONTH ) + 12 ) % 3;
				current.add( Calendar.MONTH, -monthRemaindary );
				// Do not exceed the max days of current month
				current.set( Calendar.DATE,
						Math.min( start.get( Calendar.DATE ),
								current.getActualMaximum( Calendar.DATE ) ) );
			}
			return current.getTime( );
		}
	}
