public class AddJoinConditionCommand extends Command
{

	protected EditPart source;
	protected ColumnEditPart target;

	/**
	 * Standard for constructor for a compound command.
	 * 
	 * @param domain
	 *            The editing domain
	 * @param owner
	 *            The object to be modified
	 * @param value
	 *            The value to "set"
	 */
	public AddJoinConditionCommand( final EditPart source,
			final ColumnEditPart target )
	{
		super( );
		this.source = source;
		this.target = target;
	}

	public boolean canExecute( )
	{
		// return super.canExecute();
		boolean canExecute = ( target != null && source != null );

		return canExecute;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute( )
	{

		if ( source == null || target == null )
		{
			return;
		}

		DimensionJoinCondition joinCondition = StructureFactory.createDimensionJoinCondition( );
		joinCondition.setCubeKey( target.getColumnName( ) );
		if ( source instanceof HierarchyColumnEditPart )
			joinCondition.setHierarchyKey( ( (HierarchyColumnEditPart) source ).getColumnName( ) );

		TabularHierarchyHandle hierarchy = (TabularHierarchyHandle) ( (HierarchyNodeEditPart) source.getParent( ) ).getModel( );

		try
		{
			TabularCubeHandle cube = ( (DatasetNodeEditPart) target.getParent( ) ).getCube( );
			getDimensionCondition( cube,
					hierarchy ).addJoinCondition( joinCondition );

		}
		catch ( Exception e )
		{
			ExceptionUtil.handle( e );
		}

	}

	private DimensionConditionHandle getDimensionCondition(
			TabularCubeHandle cube, TabularHierarchyHandle hierarchy )
			throws Exception
	{
		DimensionConditionHandle conditionHandle = cube.findDimensionCondition( hierarchy );
		if ( conditionHandle != null )
			return conditionHandle;
		DimensionCondition dimensionCondition = StructureFactory.createCubeJoinCondition( );
		conditionHandle = cube.addDimensionCondition( dimensionCondition );
		conditionHandle.setHierarchy( hierarchy );
		return conditionHandle;
	}
}
