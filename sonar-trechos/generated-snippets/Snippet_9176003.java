public abstract class AbstractChartIntSpinner extends Composite
{

	public AbstractChartIntSpinner( Composite parent, int style )
	{
		super( parent, style );
	}

	abstract public void setValue( int value );

	abstract public int getValue( );

	abstract public void setIncrement( int increment );

	abstract public void setMaximum( int max );

	abstract public void setMinimum( int min );

	abstract public void addListener( Listener listener );
	
	abstract public boolean isSpinnerEnabled( );
}
