public class Snippet__9491268 {

    @Override
      public void handleFailedContainer(TaskAttemptId attemptID) {
        toBePreempted.remove(attemptID);
        checkpoints.remove(attemptID.getTaskId());
      }

}
