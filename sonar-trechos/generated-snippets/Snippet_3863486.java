@Singleton
public class HostStateDAO {
  @Inject
  Provider<EntityManager> entityManagerProvider;
  @Inject
  DaoUtils daoUtils;


  @RequiresSession
  public HostStateEntity findByHostId(Long hostId) {
    return entityManagerProvider.get().find(HostStateEntity.class, hostId);
  }

  @RequiresSession
  public List<HostStateEntity> findAll() {
    return daoUtils.selectAll(entityManagerProvider.get(), HostStateEntity.class);
  }

  @Transactional
  public void refresh(HostStateEntity hostStateEntity) {
    entityManagerProvider.get().refresh(hostStateEntity);
  }

  @Transactional
  public void create(HostStateEntity hostStateEntity) {
    entityManagerProvider.get().persist(hostStateEntity);
  }

  @Transactional
  public HostStateEntity merge(HostStateEntity hostStateEntity) {
    return entityManagerProvider.get().merge(hostStateEntity);
  }

  @Transactional
  public void remove(HostStateEntity hostStateEntity) {
    entityManagerProvider.get().remove(merge(hostStateEntity));
  }

  @Transactional
  public void removeByHostId(Long hostId) {
    remove(findByHostId(hostId));
  }

}
