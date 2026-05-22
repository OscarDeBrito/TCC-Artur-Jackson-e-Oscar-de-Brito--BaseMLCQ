public class Snippet__3785309 {

    @Override
      protected void runLater(State state, RandWalkEnv env) throws Exception {
        Text[] points = getRandomTabletRange(state);
        log.info("merging " + rangeToString(points));
        env.getAccumuloClient().tableOperations().merge(Setup.getTableName(), points[0], points[1]);
        log.info("merging " + rangeToString(points) + " complete");
      }

}
