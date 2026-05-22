public class Snippet__6820808 {

    @AutoValue
      abstract static class CompiledForeachRangeArgs {
        /** Current loop index. */
        abstract Expression start();

        /** Where to end loop iteration, defaults to {@code 0}. */
        abstract Expression end();

        /** This statement will increment the index by the loop stride. */
        abstract Expression step();

        /** Statements that must have been run prior to using any of the above expressions. */
        abstract ImmutableList<Statement> initStatements();
      }

}
