public class SessionMap
{
    @SuppressWarnings("unchecked")
    public static <T> T get(String objectName, Class<T> type)
    {
        Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        return (T) map.get(objectName);
    }

    public static <T> T get(Page page, String propertyName, Class<T> type)
    {
        String objectName = page.getPageName() + "." + propertyName + "." + type.getSimpleName();
        return get(objectName, type);
    }
    
    public static <T> void put(String objectName, Class<T> type, T object)
    {
        Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (object != null)
            map.put(objectName, object);
        else
            map.remove(objectName);
    }
    
    public static <T> void remove(Page page, String propertyName, Class<T> type, T object)
    {
        String objectName = page.getPageName() + "." + propertyName + "." + type.getSimpleName();
        put(objectName, type, object); 
    }
    
    public static <T> void remove(String objectName, Class<T> type)
    {
        put(objectName, type, null); 
    }
    
    public static <T> void remove(Page page, String propertyName, Class<T> type)
    {
        remove(page, propertyName, type, null); 
    }
}
