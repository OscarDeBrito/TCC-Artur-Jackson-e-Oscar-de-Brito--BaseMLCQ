public class Snippet__9539712 {

    @Override
      protected void serviceInit(Configuration conf) throws Exception {
        this.conf = conf;

        // create scheduler executor service that services the cleaner tasks
        // use 2 threads to accommodate the on-demand tasks and reduce the chance of
        // back-to-back runs
        ThreadFactory tf =
            new ThreadFactoryBuilder().setNameFormat("Shared cache cleaner").build();
        scheduledExecutor = HadoopExecutors.newScheduledThreadPool(2, tf);
        super.serviceInit(conf);
      }

}
