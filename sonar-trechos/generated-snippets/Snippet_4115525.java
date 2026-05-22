public class Snippet__4115525 {

    public abstract static class AbstractBrooklynAdjunctSerialization<T extends BrooklynObject & EntityAdjunct> extends AbstractWithManagementContextSerialization<T> {
            public AbstractBrooklynAdjunctSerialization(Class<T> type, ManagementContext mgmt) { 
                super(type, mgmt);
            }
            @Override
            protected String customType(T value) throws IOException {
                return type.getCanonicalName();
            }
            @Override
            public void customWriteBody(T value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
                Optional<String> entityId = getEntityId(value);
                jgen.writeStringField("id", value.getId());
                if (entityId.isPresent()) jgen.writeStringField("entityId", entityId.get());
            }
            @Override
            protected T customReadBody(String type, Map<Object, Object> values, JsonParser jp, DeserializationContext ctxt) throws IOException {
                Optional<String> entityId = Optional.fromNullable((String) values.get("entityId"));
                Optional<Entity> entity = Optional.fromNullable(entityId.isPresent() ? null: mgmt.getEntityManager().getEntity(entityId.get()));
                String id = (String) values.get("id");
                return getInstanceFromId(entity, id);
            }
            protected Optional<String> getEntityId(T value) {
                if (value instanceof AbstractEntityAdjunct) {
                    Entity entity = ((AbstractEntityAdjunct)value).getEntity();
                    return Optional.fromNullable(entity == null ? null : entity.getId());
                }
                return Optional.absent();
            }
            protected abstract T getInstanceFromId(Optional<Entity> entity, String id);
        }

}
