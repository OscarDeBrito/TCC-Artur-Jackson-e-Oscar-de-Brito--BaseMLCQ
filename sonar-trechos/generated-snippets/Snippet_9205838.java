public interface IResultSet
{
	/**
	 * Get resultset axis for row edge.
	 * @return
	 */
	public IEdgeAxis getRowEdgeResult( );
	
	/**
	 * Get resultset axis for column edge.
	 * @return
	 */
	public IEdgeAxis getColumnEdgeResult( );

	/**
	 * Get resultset axis for page edge.
	 * @return
	 */
	public IEdgeAxis getPageEdgeResult( );
	
	/**
	 * Get resultset axis for all aggregation.
	 * @return
	 */
	public IEdgeAxis[] getMeasureResult( );

	/**
	 * Get resultset axis for certain aggregation.
	 * @param name
	 * @return
	 */
	public IEdgeAxis getMeasureResult( String name )  throws DataException;

}
