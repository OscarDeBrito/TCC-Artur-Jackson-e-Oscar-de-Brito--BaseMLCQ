public class Snippet__3803712 {

    private void updateWindowState(List<Tuple> expired, List<Tuple> newEvents) {
            LOG.debug("Update window state, {} expired, {} new events", expired.size(), newEvents.size());
            Map<TaskStream, WindowState> state = new HashMap<>();
            updateState(state, expired, false);
            updateState(state, newEvents, true);
            updateStreamState(state);
        }

}
