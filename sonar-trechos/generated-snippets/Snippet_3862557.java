public class ServiceComponentHostEventWrapper {

  private ServiceComponentHostEvent event = null;
  private String eventJson = null;

  public ServiceComponentHostEventWrapper(ServiceComponentHostEvent event) {
    this.event  = event;
  }
  
  public ServiceComponentHostEventWrapper(String eventJson) {
    this.eventJson = eventJson;
  }

  public ServiceComponentHostEvent getEvent() {
    if (event != null) {
      return event;
    } else if (eventJson != null) {
      try {
        event = StageUtils.fromJson(eventJson, ServiceComponentHostEvent.class);
        return event;
      } catch (IOException e) {
        throw new RuntimeException("Illegal Json for event", e);
      }
    }
    return null;
  }
  
  public String getEventJson() { 
    if (eventJson != null) {
      return eventJson;
    } else if (event != null) {
      try {
        eventJson = StageUtils.jaxbToString(event);
        return eventJson;
      } catch (JAXBException | IOException e) {
        throw new RuntimeException("Couldn't get json", e);
      }
    } else {
      return null;
    }
  }
  
  public String toString() {
    if (event != null) {
      return event.toString();
    } else if (eventJson != null) {
      return eventJson;
    }
    return "null";
  }
}
