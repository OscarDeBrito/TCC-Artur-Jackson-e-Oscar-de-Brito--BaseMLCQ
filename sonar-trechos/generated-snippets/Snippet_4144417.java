public class Snippet__4144417 {

    @Deprecated // to be removed before 2.0
      public static RelNode swap(Join join) {
        return swap(join, false,
            RelFactories.LOGICAL_BUILDER.create(join.getCluster(), null));
      }

}
