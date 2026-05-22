public class Snippet__8905843 {

    private static String applyTimestampFormat(String arg, String format, TimeZone timeZone) {
        return DruidExpressions.functionCall("timestamp_format",
            ImmutableList.of(arg, DruidExpressions.stringLiteral(format), DruidExpressions.stringLiteral(timeZone.getID()))
        );
      }

}
