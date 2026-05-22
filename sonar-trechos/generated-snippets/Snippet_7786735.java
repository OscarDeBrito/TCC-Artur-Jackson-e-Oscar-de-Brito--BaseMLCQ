public abstract class DataView<T> extends DataViewBase<T>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 *            component id
	 * @param dataProvider
	 *            data provider
	 */
	protected DataView(String id, IDataProvider<T> dataProvider)
	{
		super(id, dataProvider);
	}

	/**
	 * @param id
	 *            component id
	 * @param dataProvider
	 *            data provider
	 * @param itemsPerPage
	 *            items per page
	 */
	protected DataView(String id, IDataProvider<T> dataProvider, long itemsPerPage)
	{
		super(id, dataProvider);
		setItemsPerPage(itemsPerPage);
	}

	/**
	 * @return data provider
	 */
	public IDataProvider<T> getDataProvider()
	{
		return internalGetDataProvider();
	}

}
