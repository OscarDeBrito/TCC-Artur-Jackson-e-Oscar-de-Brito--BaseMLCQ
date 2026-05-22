public class Snippet__4689128 {

    private void removeTaskFromJob(BSPJobID jobId, TaskInProgress tip) {
        synchronized (runningJobs) {
          RunningJob rjob = runningJobs.get(jobId);
          if (rjob == null) {
            LOG.warn("Unknown job " + jobId + " being deleted.");
          } else {
            synchronized (rjob) {
              rjob.tasks.remove(tip);
            }
          }
        }
      }

}
