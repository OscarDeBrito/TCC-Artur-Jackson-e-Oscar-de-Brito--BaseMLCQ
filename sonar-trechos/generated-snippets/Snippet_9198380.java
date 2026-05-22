public class RunningCount extends AggregateFunctionAdapter
{
	/* (non-Javadoc)
	 * @see org.eclipse.birt.chart.aggregate.AggregateFunctionAdapter#getType()
	 */
	public int getType( )
	{
		return RUNNING_AGGR;
	}

	@Override
	public int getBIRTDataType( )
	{
		return DataType.INTEGER_TYPE;
	}
}
