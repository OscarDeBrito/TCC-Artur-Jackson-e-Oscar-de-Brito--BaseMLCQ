public class IdMapKeyAccessor implements Accessor {

    public static final Accessor SHARED_ACCESSOR = new IdMapKeyAccessor();

    public String getName() {
        return "IdMapKeyAccessor";
    }

    public Object getValue(Object object) throws PropertyException {
        if (object instanceof Persistent) {
            ObjectId id = ((Persistent) object).getObjectId();

            if (id.isTemporary()) {
                return id;
            }

            Map<?, ?> map = id.getIdSnapshot();
            if (map.size() == 1) {
                Map.Entry<?, ?> pkEntry = map.entrySet().iterator().next();
                return pkEntry.getValue();
            }

            return id;
        }
        else {
            throw new IllegalArgumentException("Object must be Persistent: " + object);
        }
    }

    public void setValue(Object object, Object newValue) throws PropertyException {
        throw new UnsupportedOperationException("Setting map key is not supported");
    }
}
