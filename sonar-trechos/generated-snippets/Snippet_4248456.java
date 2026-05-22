public interface IDiscoveryListener
{
    /**
     * Add the service if needed. This does not necessarily mean that the service is not already
     * added. This can be called if there is a change in service information, such as the cacheNames.
     * <p>
     * @param service the service to add
     */
    void addDiscoveredService( DiscoveredService service );

    /**
     * Remove the service from the list.
     * <p>
     * @param service the service to remove
     */
    void removeDiscoveredService( DiscoveredService service );
}
