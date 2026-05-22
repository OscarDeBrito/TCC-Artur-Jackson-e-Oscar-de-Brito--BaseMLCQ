public class Snippet__4432223 {

    public static AggregateFunctionTypeMatcher matchAll(String function) {
            for (AggregateFunctionType type : values()) {
                Matcher m = type.pattern.matcher(function);
                if (m.find()) {
                    return new AggregateFunctionTypeMatcher(type, true, m.group(1));
                }
            }
            return new AggregateFunctionTypeMatcher(null, false, null);
        }

}
