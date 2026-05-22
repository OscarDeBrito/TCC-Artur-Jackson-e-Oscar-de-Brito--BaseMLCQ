public class Property extends Data
{
    public void setName(String name)
    {
        setKey(name);
    }

    public String getName()
    {
        return getKey();
    }
}
