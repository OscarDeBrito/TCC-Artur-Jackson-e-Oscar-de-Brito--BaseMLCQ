public class BeanPropertyItemSqlParameterSourceProvider<T> implements ItemSqlParameterSourceProvider<T> {

	/**
	 * Provide parameter values in an {@link BeanPropertySqlParameterSource} based on values from
	 * the provided item.
	 * @param item the item to use for parameter values
	 */
	@Override
	public SqlParameterSource createSqlParameterSource(T item) {
		return new BeanPropertySqlParameterSource(item);
	}

}
