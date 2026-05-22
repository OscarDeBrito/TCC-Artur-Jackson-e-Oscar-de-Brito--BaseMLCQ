public class NullScheduler implements IScheduler {

  @Override
  public void initialize(Config config, Config runtime) {

  }

  @Override
  public void close() {

  }

  @Override
  public boolean onSchedule(PackingPlan packing) {
    return true;
  }

  @Override
  public List<String> getJobLinks() {
    return new ArrayList<>();
  }

  @Override
  public boolean onKill(Scheduler.KillTopologyRequest request) {
    return true;
  }

  @Override
  public boolean onRestart(Scheduler.RestartTopologyRequest request) {
    return true;
  }

  @Override
  public boolean onUpdate(Scheduler.UpdateTopologyRequest request) {
    return false;
  }
}
