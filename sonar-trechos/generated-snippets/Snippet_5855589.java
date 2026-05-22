public class Snippet__5855589 {

    @Override
        public void endAccess() {
            super.endAccess() ;
            if(manager instanceof ClusterManagerBase) {
                ((ClusterManagerBase)manager).registerSessionAtReplicationValve(this);
            }
        }

}
