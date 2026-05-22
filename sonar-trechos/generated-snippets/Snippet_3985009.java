public final class ValueInstance extends TransientInstance
    implements CompositeInstance, MixinsInstance
{
    public ValueInstance( ValueModel compositeModel, Object[] mixins, ValueStateInstance state )
    {
        super( compositeModel, mixins, state );
    }

    /**
     * Perform equals with {@code o} argument.
     * <p>
     * The definition of equals() for the Value is that if both the state and descriptor are equal,
     * then the values are equal.
     * </p>
     *
     * @param o The other object to compare.
     *
     * @return Returns a {@code boolean} indicator whether this object is equals the other.
     */
    @Override
    public boolean equals( Object o )
    {
        if( this == o )
        {
            return true;
        }
        if( o == null || !Proxy.isProxyClass( o.getClass() ) )
        {
            return false;
        }

        try
        {
            ValueInstance that = (ValueInstance) Proxy.getInvocationHandler( o );
            // Descriptor equality
            if( !descriptor().equals( that.descriptor() ) )
            {
                return false;
            }
            // State equality
            return state.equals( that.state );
        }
        catch( ClassCastException e )
        {
            return false;
        }
    }

    @Override
    public ValueStateInstance state()
    {
        return (ValueStateInstance) state;
    }

    @Override
    public ValueModel descriptor()
    {
        return (ValueModel) compositeModel;
    }

    /**
     * When a ValueBuilder is about to start, ensure that all state has builder infos, i.e. they are mutable.
     */
    public void prepareToBuild()
    {
        descriptor().state().properties().forEach(
            descriptor -> ( (PropertyInstance<Object>) state.propertyFor( descriptor.accessor() ) )
                .prepareToBuild( descriptor ) );

        descriptor().state().associations().forEach(
            descriptor -> state().associationFor( descriptor.accessor() )
                                 .setAssociationInfo( descriptor.builderInfo() ) );

        descriptor().state().manyAssociations().forEach(
            descriptor -> state().manyAssociationFor( descriptor.accessor() )
                                 .setAssociationInfo( descriptor.builderInfo() ) );

        descriptor().state().namedAssociations().forEach(
            descriptor -> state().namedAssociationFor( descriptor.accessor() )
                                 .setAssociationInfo( descriptor.builderInfo() ) );
    }

    /**
     * When a ValueBuilder is finished and is about to instantiate a Value, call this to ensure that the state has correct
     * settings, i.e. is immutable.
     */
    public void prepareBuilderState()
    {
        descriptor().state().properties().forEach(
            descriptor -> ( (PropertyInstance<Object>) state.propertyFor( descriptor.accessor() ) )
                .prepareBuilderState( descriptor ) );

        descriptor().state().associations().forEach(
            descriptor -> state().associationFor( descriptor.accessor() ).setAssociationInfo( descriptor ) );

        descriptor().state().manyAssociations().forEach(
            descriptor -> state().manyAssociationFor( descriptor.accessor() ).setAssociationInfo( descriptor ) );

        descriptor().state().namedAssociations().forEach(
            descriptor -> state().namedAssociationFor( descriptor.accessor() ).setAssociationInfo( descriptor ) );
    }

    /**
     * Calculate hash code.
     *
     * @return the hashcode of this instance.
     */
    @Override
    public int hashCode()
    {
        int hash = compositeModel.hashCode() * 23; // Descriptor
        return hash + state.hashCode() * 5; // State
    }

    public String toJsonString()
    {
        Serializer serialization = ( (ModuleSpi) module().instance() ).serialization();
        if( serialization != null )
        {
            return serialization.serialize( Serializer.Options.NO_TYPE_INFO, proxy() );
        }
        return null;
    }

    @Override
    public String toString()
    {
        String json = toJsonString();
        if( json != null )
        {
            return json;
        }
        return "ValueInstance{" +
               "mixins=" + Arrays.toString( mixins ) +
               ", state=" + state +
               ", compositeModel=" + compositeModel +
               '}';
    }
}
