@InterfaceAudience.Public
@InterfaceStability.Stable
public abstract class HCatEventMessage {

  /**
   * Enumeration of all supported types of Metastore operations.
   */
  public static enum EventType {

    CREATE_DATABASE(HCatConstants.HCAT_CREATE_DATABASE_EVENT),
    DROP_DATABASE(HCatConstants.HCAT_DROP_DATABASE_EVENT),
    CREATE_TABLE(HCatConstants.HCAT_CREATE_TABLE_EVENT),
    DROP_TABLE(HCatConstants.HCAT_DROP_TABLE_EVENT),
    ADD_PARTITION(HCatConstants.HCAT_ADD_PARTITION_EVENT),
    DROP_PARTITION(HCatConstants.HCAT_DROP_PARTITION_EVENT),
    ALTER_TABLE(HCatConstants.HCAT_ALTER_TABLE_EVENT),
    ALTER_PARTITION(HCatConstants.HCAT_ALTER_PARTITION_EVENT),
    INSERT(HCatConstants.HCAT_INSERT_EVENT),
    CREATE_FUNCTION(HCatConstants.HCAT_CREATE_FUNCTION_EVENT),
    DROP_FUNCTION(HCatConstants.HCAT_DROP_FUNCTION_EVENT);

    private String typeString;

    EventType(String typeString) {
      this.typeString = typeString;
    }

    @Override
    public String toString() { return typeString; }
  }

  protected EventType eventType;

  protected HCatEventMessage(EventType eventType) {
    this.eventType = eventType;
  }

  public EventType getEventType() {
    return eventType;
  }

  /**
   * Getter for HCatalog Server's URL.
   * (This is where the event originates from.)
   * @return HCatalog Server's URL (String).
   */
  public abstract String getServer();

  /**
   * Getter for the Kerberos principal of the HCatalog service.
   * @return HCatalog Service Principal (String).
   */
  public abstract String getServicePrincipal();

  /**
   * Getter for the name of the Database on which the Metastore operation is done.
   * @return Database-name (String).
   */
  public abstract String getDB();

  /**
   * Getter for the timestamp associated with the operation.
   * @return Timestamp (Long - seconds since epoch).
   */
  public abstract Long getTimestamp();

  /**
   * Class invariant. Checked after construction or deserialization.
   */
  public HCatEventMessage checkValid() {
    if (getServer() == null || getServicePrincipal() == null) {
      throw new IllegalStateException("Server-URL/Service-Principal shouldn't be null.");
    }
    if (getEventType() == null) {
      throw new IllegalStateException("Event-type unset.");
    }
    if (getDB() == null) {
      throw new IllegalArgumentException("DB-name unset.");
    }
    return this;
  }
}
