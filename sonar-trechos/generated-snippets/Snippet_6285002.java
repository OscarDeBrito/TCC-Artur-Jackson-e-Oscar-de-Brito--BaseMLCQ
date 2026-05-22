public interface ColumnPreparedStatement {


    /**
     * Binds an argument to a positional parameter.
     *
     * @param name  the parameter name
     * @param value the parameter value
     * @return the same query instance
     * @throws NullPointerException     when there is null parameter
     */
    ColumnPreparedStatement bind(String name, Object value);

    /**
     * Executes a query and return the result as List
     *
     * @return The result list, if delete it will return an empty list
     */
    List<ColumnEntity> getResultList();

    /**
     * Returns the result as a single element otherwise it will return an {@link Optional#empty()}
     *
     * @return the single result
     * @throws org.jnosql.diana.api.NonUniqueResultException when the result has more than one entity
     */
    Optional<ColumnEntity> getSingleResult();

}
