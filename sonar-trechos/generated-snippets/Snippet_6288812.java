public interface DocumentEntityPrePersist {

    /**
     * The {@link DocumentEntity}  before be saved
     *
     * @return the {@link DocumentEntity} instance
     */
    DocumentEntity getEntity();

    /**
     * Creates the {@link DocumentEntityPrePersist} instance
     *
     * @param entity the entity
     * @return {@link DocumentEntityPrePersist} instance
     * @throws NullPointerException when the entity is null
     */
    static DocumentEntityPrePersist of(DocumentEntity entity) {
        Objects.requireNonNull(entity, "Entity is required");
        return new DefaultDocumentEntityPrePersist(entity);
    }
}
