public class Snippet__5678475 {

    @Override
        public String execute(Job job) {
            String id = JobManagerImpl.buildId(job);
            Future<?> future = this.pool.submit(job);
            synchronized (taskMap) {
                taskMap.put(id, future);
                locations.put(id, job.buildResultLocation(id));
                return id;
            }
        }

}
