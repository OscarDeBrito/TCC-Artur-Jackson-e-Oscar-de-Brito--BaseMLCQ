interface SelectWithTable<T> extends SelectWithQuery<T> {

		/**
		 * Explicitly set the {@link String name} of the table on which to perform the query.
		 * <p>
		 * Skip this step to use the default table derived from the {@link Class domain type}.
		 *
		 * @param table {@link String name} of the table; must not be {@literal null} or empty.
		 * @return new instance of {@link SelectWithProjection}.
		 * @throws IllegalArgumentException if {@link String table} is {@literal null} or empty.
		 * @see #inTable(CqlIdentifier)
		 * @see SelectWithProjection
		 */
		default SelectWithProjection<T> inTable(String table) {

			Assert.hasText(table, "Table name must not be null or empty");

			return inTable(CqlIdentifier.of(table));
		}

		/**
		 * Explicitly set the {@link CqlIdentifier name} of the table on which to perform the query.
		 * <p>
		 * Skip this step to use the default table derived from the {@link Class domain type}.
		 *
		 * @param table {@link CqlIdentifier name} of the table; must not be {@literal null}.
		 * @return new instance of {@link SelectWithProjection}.
		 * @throws IllegalArgumentException if {@link CqlIdentifier table} is {@literal null}.
		 * @see org.springframework.data.cassandra.core.cql.CqlIdentifier
		 * @see SelectWithProjection
		 */
		SelectWithProjection<T> inTable(CqlIdentifier table);

	}
