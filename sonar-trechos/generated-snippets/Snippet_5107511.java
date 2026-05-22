public class ValueCommons {

    public static Function<Value,String> stringValue() {
        return STRING_VALUE;
    }



    private static Function<Value, String> STRING_VALUE = new Function<Value, String>() {
        @Override
        public String apply(Value input) {
            return input.stringValue();
        }
    };

}
