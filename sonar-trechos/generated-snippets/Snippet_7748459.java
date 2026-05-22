public class PlatformComponentTemplate extends AbstractResource {

    public static final String CAMP_TYPE = "PlatformComponentTemplate";
    static { assert CAMP_TYPE.equals(PlatformComponentTemplate.class.getSimpleName()); }
    
    /** Use {@link #builder()} to create */
    protected PlatformComponentTemplate() {}

    
    // no fields beyond basic resource
    
    
    // builder
    
    public static Builder<? extends PlatformComponentTemplate> builder() {
        return new PlatformComponentTemplate().new Builder<PlatformComponentTemplate>(CAMP_TYPE);
    }
    
    public class Builder<T extends PlatformComponentTemplate> extends AbstractResource.Builder<T,Builder<T>> {
        
        protected Builder(String type) { super(type); }
        
//        public Builder<T> foo(String x) { instance().setFoo(x); return thisBuilder(); }
    }

}
