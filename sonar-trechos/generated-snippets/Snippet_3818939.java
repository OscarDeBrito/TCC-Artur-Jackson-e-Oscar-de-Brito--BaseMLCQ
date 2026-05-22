public class Snippet__3818939 {

    public Snippet__3818939() {
          try {
             directoryService = new DefaultDirectoryService();
          } catch (Exception e) {
             throw new RuntimeException(e);
          }
          directoryService.setShutdownHookEnabled(false);
          partitionFactory = new AvlPartitionFactory();
       }

}
