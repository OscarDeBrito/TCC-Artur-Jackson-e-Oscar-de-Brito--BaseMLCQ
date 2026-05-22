public class Snippet__5548181 {

    @VisibleForTesting
       Set<MPath> getMAuthzPaths(long authzSnapshotID, String authzObj) throws Exception {
        return tm.executeTransactionWithRetry( pm -> {
          MAuthzPathsMapping mapping = null;
          pm.setDetachAllOnCommit(true); // No need to detach objects
          mapping = getMAuthzPathsMappingCore(pm, authzSnapshotID, authzObj);
          if(mapping != null) {
            Set<MPath> paths = mapping.getPathsPersisted();
            return paths;
          } else {
            return Collections.emptySet();
          }
        });
      }

}
