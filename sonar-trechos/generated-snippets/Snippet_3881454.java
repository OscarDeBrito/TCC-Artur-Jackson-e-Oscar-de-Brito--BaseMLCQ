public class HostEventCreator implements RequestAuditEventCreator {

  /**
   * Set of {@link Request.Type}s that are handled by this plugin
   */
  private Set<Request.Type> requestTypes = ImmutableSet.<Request.Type>builder().add(Request.Type.QUERY_POST, Request.Type.POST, Request.Type.DELETE).build();

  /**
   * Set of {@link Resource.Type}s that are handled by this plugin
   */
  private Set<Resource.Type> resourceTypes = ImmutableSet.<Resource.Type>builder().add(Resource.Type.Host).build();

  /**
   * Pattern to retrieve hostname from url
   */
  private static final Pattern HOSTNAME_PATTERN = Pattern.compile(".*" + HostResourceProvider.HOST_HOST_NAME_PROPERTY_ID + "\\s*=\\s*([^&\\s]+).*");

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<Request.Type> getRequestTypes() {
    return requestTypes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<Resource.Type> getResourceTypes() {
    return resourceTypes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<ResultStatus.STATUS> getResultStatuses() {
    // null makes this default
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public AuditEvent createAuditEvent(Request request, Result result) {

    switch (request.getRequestType()) {
      case DELETE:
        return DeleteHostRequestAuditEvent.builder()
          .withTimestamp(System.currentTimeMillis())
          .withRequestType(request.getRequestType())
          .withResultStatus(result.getStatus())
          .withUrl(request.getURI())
          .withRemoteIp(request.getRemoteAddress())
          .withHostName(request.getResource().getKeyValueMap().get(Resource.Type.Host))
          .build();
      case POST:
        return AddHostRequestAuditEvent.builder()
          .withTimestamp(System.currentTimeMillis())
          .withRequestType(request.getRequestType())
          .withResultStatus(result.getStatus())
          .withUrl(request.getURI())
          .withRemoteIp(request.getRemoteAddress())
          .withHostName(RequestAuditEventCreatorHelper.getNamedProperty(request, HostResourceProvider.HOST_HOST_NAME_PROPERTY_ID))
          .build();
      case QUERY_POST:
        return AddComponentToHostRequestAuditEvent.builder()
          .withTimestamp(System.currentTimeMillis())
          .withRequestType(request.getRequestType())
          .withResultStatus(result.getStatus())
          .withUrl(request.getURI())
          .withRemoteIp(request.getRemoteAddress())
          .withHostName(getHostNameFromQuery(request))
          .withComponents(getHostComponents(request))
          .build();
      default:
        return null;
    }
  }

  /**
   * Returns component name from the request
   * @param request
   * @return
   */
  private Set<String> getHostComponents(Request request) {
    Set<String> components = new HashSet<>();
    NamedPropertySet propertySet = Iterables.getFirst(request.getBody().getNamedPropertySets(), null);
    if (propertySet != null && propertySet.getProperties().get("host_components") instanceof Set) {
      Set<Map<String, String>> set = (Set<Map<String, String>>) propertySet.getProperties().get("host_components");
      if (set != null && !set.isEmpty()) {
        for(Map<String, String> element : set) {
          components.add(element.get(HostComponentResourceProvider.COMPONENT_NAME));
        }
      }
    }
    return components;
  }

  /**
   * Returns hostname from the query string of the request
   * @param request
   * @return
   */
  private String getHostNameFromQuery(Request request) {
    Matcher matcher = HOSTNAME_PATTERN.matcher(request.getURI());
    if(matcher.find()) {
      return matcher.group(1);
    }
    return null;
  }
}
