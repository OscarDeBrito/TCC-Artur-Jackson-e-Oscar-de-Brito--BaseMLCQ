public class NoOpStepContext implements StepContext, Serializable {

  @Override
  public StateInternals stateInternals() {
    throw new UnsupportedOperationException();
  }

  @Override
  public TimerInternals timerInternals() {
    throw new UnsupportedOperationException();
  }
}
