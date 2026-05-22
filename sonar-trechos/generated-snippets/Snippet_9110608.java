public class Snippet__9110608 {

    public void onRow( boolean populateValue, FactTableRow factTableRow )
    			throws DataException
    	{
    		for ( int aggrIndex = 0; aggrIndex < this.functions.length; aggrIndex++ )
    		{
    			Accumulator acc = this.accumulators.get( aggrIndex );
			
    			// Calculate arguments to the aggregate aggregationtion

    			acc.onRow( new Object[]{factTableRow.getMeasures( )[aggrIndex]} );
			

    			if ( populateValue )
    			{
    				acc.finish( );
    				currentRoundAggrValue[aggrIndex] = acc.getValue( );
    				acc.start( );
    			}
    		}
    	}

}
