public interface MvccLogEntrySerializationStrategy extends Migration, VersionedData {

    /**
     * Serialize the entity to the data store with the given collection context
     *
     * @param applicationScope The applicationscope of the entrye
     * @param entry the entry to write
     *
     * @return The mutation batch with the mutation operations for this write.
     */
    MutationBatch write( final ApplicationScope applicationScope, MvccLogEntry entry );

    /**
     * Load and return the stage with the given id and a version that is <= the version provided
     *
     * @param applicationScope The applicationScope to persist the entity into
     * @param entityIds The entity id to load
     * @param version The max version to load.  This will return the first version <= the given version
     *
     * @return The deserialized version of the log entry
     */
    VersionSet load( final ApplicationScope applicationScope, final Collection<Id> entityIds, final UUID version );

    /**
     * Load a list, from highest to lowest of the stage with versions <= version up to maxSize elements
     *
     * @param applicationScope The applicationScope to load the entity from
     * @param entityId The entity id to load
     * @param version The max version to seek from
     * @param maxSize The maximum size to return.  If you receive this size, there may be more versions to load.
     *
     * @return A list of entities up to max size ordered from max(UUID)=> min(UUID)
     */
    List<MvccLogEntry> load( ApplicationScope applicationScope, Id entityId, UUID version, int maxSize );



    /**
     * Load a list, from lowest to highest of the stage with versions <= version up to maxSize elements
     *
     * @param applicationScope The applicationScope to load the entity from
     * @param entityId The entity id to load
     * @param minVersion The min version to seek from.  Null is allowed
     * @param maxSize The maximum size to return.  If you receive this size, there may be more versions to load.
     *
     * @return A list of entities up to max size ordered from max(UUID)=> min(UUID)
     */
    List<MvccLogEntry> loadReversed( ApplicationScope applicationScope, Id entityId, UUID minVersion, int maxSize );

    /**
     * MarkCommit the stage from the applicationScope with the given entityId and version
     *
     * @param applicationScope The applicationScope that contains the entity
     * @param entityId The entity id to delete
     * @param version The version to delete
     */
    MutationBatch delete( ApplicationScope applicationScope, Id entityId, UUID version );
}
