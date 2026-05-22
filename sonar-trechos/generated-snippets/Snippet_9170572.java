public class Snippet__9170572 {

    public Object calculate( Object value )
    	{
    		if ( value == null )
    		{
    			return new Double( -1 );
    		}

    		if ( intervalStart == null )
    		{
    			return new Double( Math.floor( this.dateTimeUtil.diffYear( defaultStart,
    					(Date) value )
    					/ getDateIntervalRange( ) ) );
    		}
    		else
    		{
    			if ( this.dateTimeUtil.diffYear( (Date) intervalStart, (Date) value ) < 0 )
    			{
    				return new Double( -1 );
    			}
    			else
    			{
    				return new Double( Math.floor( this.dateTimeUtil.diffYear( (Date) intervalStart,
    						(Date) value )
    						/ getDateIntervalRange( ) ) );
    			}
    		}
    	}

}
