public class SuiteInProgressInfo extends TestInProgressInfo {

  private final TestState testState;

  public SuiteInProgressInfo(TestState testState) {
    this.testState = testState;
  }

  @Override
  public boolean isProblem() {
    for (TestState state : testState.getChildren()) {
      if (state.isProblem()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public TestStateDescription getDescription() {
    return TestStateDescription.RUNNING;
  }
}
