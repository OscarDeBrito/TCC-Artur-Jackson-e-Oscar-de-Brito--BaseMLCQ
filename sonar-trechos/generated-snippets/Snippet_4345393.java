public final class EscalateContainerKey implements Matchable
{

    /**************************************************************************
     * Fields of the class
     **************************************************************************
     */
    private ContainerKey container_key;

    /**************************************************************************
     * Constructors for This class:
     **************************************************************************
     */
    public EscalateContainerKey(ContainerKey key)
    {
        container_key = key;
    }

    /**************************************************************************
     * Private/Protected methods of This class:
     **************************************************************************
     */

    /**************************************************************************
     * Public Methods of This class:
     **************************************************************************
     */

    /**************************************************************************
     * Public Methods of XXXX class:
     **************************************************************************
     */
	public boolean match(Object key) 
    {
		if (key instanceof RecordHandle) 
        {
			return(container_key.equals(((RecordHandle) key).getContainerId()));
		}

		return false;
	}
}
