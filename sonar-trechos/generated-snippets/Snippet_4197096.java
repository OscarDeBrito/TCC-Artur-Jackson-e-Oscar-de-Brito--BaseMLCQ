public class QualifiedName
{
    /**
     * The keyspace name as stored internally.
     */
    private String keyspace;
    private String name;

    public QualifiedName()
    {
    }

    public QualifiedName(String keyspace, String name)
    {
        this.keyspace = keyspace;
        this.name = name;
    }

    /**
     * Sets the keyspace.
     *
     * @param ks the keyspace name
     * @param keepCase <code>true</code> if the case must be kept, <code>false</code> otherwise.
     */
    public final void setKeyspace(String ks, boolean keepCase)
    {
        keyspace = toInternalName(ks, keepCase);
    }

    /**
     * Checks if the keyspace is specified.
     * @return <code>true</code> if the keyspace is specified, <code>false</code> otherwise.
     */
    public final boolean hasKeyspace()
    {
        return keyspace != null;
    }

    public final String getKeyspace()
    {
        return keyspace;
    }

    public void setName(String cf, boolean keepCase)
    {
        name = toInternalName(cf, keepCase);
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return hasKeyspace()
             ? String.format("%s.%s", keyspace, name)
             : name;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(keyspace, name);
    }

    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (!(o instanceof QualifiedName))
            return false;

        QualifiedName qn = (QualifiedName) o;
        return Objects.equals(keyspace, qn.keyspace) && name.equals(qn.name);
    }

    /**
     * Converts the specified name into the name used internally.
     *
     * @param name the name
     * @param keepCase <code>true</code> if the case must be kept, <code>false</code> otherwise.
     * @return the name used internally.
     */
    private static String toInternalName(String name, boolean keepCase)
    {
        return keepCase ? name : name.toLowerCase(Locale.US);
    }
}
