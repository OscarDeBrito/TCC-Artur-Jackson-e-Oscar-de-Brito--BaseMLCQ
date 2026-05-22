public class Snippet__3882178 {

    @RequiresSession
      public List<ClusterConfigEntity> getLatestConfigurations(long clusterId,
          StackId stackId) {
        StackEntity stackEntity = stackDAO.find(stackId.getStackName(),
            stackId.getStackVersion());

        TypedQuery<ClusterConfigEntity> query = entityManagerProvider.get().createNamedQuery(
            "ClusterConfigEntity.findLatestConfigsByStack",
            ClusterConfigEntity.class);

        query.setParameter("clusterId", clusterId);
        query.setParameter("stack", stackEntity);

        return daoUtils.selectList(query);
      }

}
