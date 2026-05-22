@Singleton
@Provider
public class JAXBContextResolver implements ContextResolver<JAXBContext> {

  private JAXBContext context;
  private final Set<Class> types;

  // you have to specify all the dao classes here
  private final Class[] cTypes = { AppInfo.class, AppAttemptInfo.class,
      AppAttemptsInfo.class, ClusterInfo.class,
      CapacitySchedulerQueueInfo.class, FifoSchedulerInfo.class,
      SchedulerTypeInfo.class, NodeInfo.class, UserMetricsInfo.class,
      CapacitySchedulerInfo.class, ClusterMetricsInfo.class,
      SchedulerInfo.class, AppsInfo.class, NodesInfo.class,
      RemoteExceptionData.class, CapacitySchedulerQueueInfoList.class,
      ResourceInfo.class, UsersInfo.class, UserInfo.class,
      ApplicationStatisticsInfo.class, StatisticsItemInfo.class};

  public JAXBContextResolver() throws Exception {
    this.types = new HashSet<Class>(Arrays.asList(cTypes));
    this.context = new JSONJAXBContext(JSONConfiguration.natural()
        .rootUnwrapping(false).build(), cTypes);
  }

  @Override
  public JAXBContext getContext(Class<?> objectType) {
    return (types.contains(objectType)) ? context : null;
  }
}
