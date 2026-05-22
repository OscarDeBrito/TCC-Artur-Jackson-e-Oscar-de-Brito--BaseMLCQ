public class Snippet__4462446 {

    public void configure(OptionManager optionsManager) {
        assert (state == State.CONFIG);
        accuracy = optionsManager.getLong(ExecConstants.HLL_ACCURACY);
        // Now config complete - moving to MERGE state
        state = State.MERGE;
      }

}
