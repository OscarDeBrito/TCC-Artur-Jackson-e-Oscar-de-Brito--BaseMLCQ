public class SimpleMapModel extends WrappingTemplateModel 
implements TemplateHashModelEx2, TemplateMethodModelEx, AdapterTemplateModel, 
WrapperTemplateModel, TemplateModelWithAPISupport {
    static final ModelFactory FACTORY =
        new ModelFactory()
        {
            public TemplateModel create(Object object, ObjectWrapper wrapper) {
                return new SimpleMapModel((Map) object, (BeansWrapper) wrapper);
            }
        };

    private final Map map;
    
    public SimpleMapModel(Map map, BeansWrapper wrapper) {
        super(wrapper);
        this.map = map;
    }

    public TemplateModel get(String key) throws TemplateModelException {
        Object val = map.get(key);
        if (val == null) {
            if (key.length() == 1) {
                // just check for Character key if this is a single-character string
                Character charKey = Character.valueOf(key.charAt(0));
                val = map.get(charKey);
                if (val == null && !(map.containsKey(key) || map.containsKey(charKey))) {
                    return null;
                }
            } else if (!map.containsKey(key)) {
                return null;
            }
        }
        return wrap(val);
    }
    
    public Object exec(List args) throws TemplateModelException {
        Object key = ((BeansWrapper) getObjectWrapper()).unwrap((TemplateModel) args.get(0));
        Object value = map.get(key);
        if (value == null && !map.containsKey(key)) {
            return null;
        }
        return wrap(value);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public int size() {
        return map.size();
    }

    public TemplateCollectionModel keys() {
        return new CollectionAndSequence(new SimpleSequence(map.keySet(), getObjectWrapper()));
    }

    public TemplateCollectionModel values() {
        return new CollectionAndSequence(new SimpleSequence(map.values(), getObjectWrapper()));
    }
    
    public KeyValuePairIterator keyValuePairIterator() {
        return new MapKeyValuePairIterator(map, getObjectWrapper());
    }

    public Object getAdaptedObject(Class hint) {
        return map;
    }
    
    public Object getWrappedObject() {
        return map;
    }

    public TemplateModel getAPI() throws TemplateModelException {
        return ((RichObjectWrapper) getObjectWrapper()).wrapAsAPI(map);
    }
}
