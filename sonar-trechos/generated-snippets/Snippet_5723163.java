public class Snippet__5723163 {

    @Override
      public Expr visitDatetime_literal(@NotNull Datetime_literalContext ctx) {
        if (checkIfExist(ctx.time_literal())) {
          return visitTime_literal(ctx.time_literal());
        } else if (checkIfExist(ctx.date_literal())) {
          return visitDate_literal(ctx.date_literal());
        } else if (checkIfExist(ctx.interval_literal())) {
          return visitInterval_literal(ctx.interval_literal());
        } else {
          return visitTimestamp_literal(ctx.timestamp_literal());
        }
      }

}
