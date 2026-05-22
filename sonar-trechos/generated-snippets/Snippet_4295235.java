class EnvironmentPropertyConfigSource extends MapConfigSource
{

    EnvironmentPropertyConfigSource()
    {
        super(System.getenv());
        initOrdinal(300);
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getConfigName()
    {
        return "environment-properties";
    }

    @Override
    public String getPropertyValue(String key)
    {
        String val = super.getPropertyValue(key);
        if (val == null || val.isEmpty())
        {
            val = super.getPropertyValue(key.replace('.', '_'));
        }

        return val;
    }

    @Override
    public boolean isScannable()
    {
        return true;
    }
}
