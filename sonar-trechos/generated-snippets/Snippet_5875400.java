public interface SearchByEdgeType {

    /**
     * Get the Id of the node of this edge
     * @return
     */
    Id getNode();


    /**
     * Get the name of the edge
     * @return
     */
    String getType();

    /**
     * Get the Maximum Version of an edge we can return.
     * This should always be a type 1 time uuid.
     * @return
     */
    long getMaxTimestamp();

    /**
     * The optional start parameter.  All edges emitted with be > the specified start edge.
     * This is useful for paging.  Simply use the last value returned in the previous call in the start parameter
     * @return
     */
    Optional<Edge> last();

    /**
     * Get the direction we're seeking
     * @return
     */
    Order getOrder();

    /**
     * Return true to filter marked edges from the results
     * @return
     */
    boolean filterMarked();


    /**
     * Options for ordering.  By default, we want to perform descending for common use cases and read speed.  This is our our data
     * is optimized in cassandra
     */
    enum Order {
        DESCENDING,
        ASCENDING
    }

}
