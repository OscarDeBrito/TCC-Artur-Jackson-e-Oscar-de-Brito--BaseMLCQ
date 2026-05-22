public class Snippet__7759750 {

    @Override
      public ParseSpec withTimestampSpec(TimestampSpec spec)
      {
        return new TimeAndDimsParseSpec(spec, getDimensionsSpec());
      }

}
