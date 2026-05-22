public class Snippet__5688699 {

    public static final class Builder {
            protected ResultTypeConfig target;

            public Builder(String name, String className) {
                target = new ResultTypeConfig(name, className);
            }

            public Builder(ResultTypeConfig orig) {
                target = new ResultTypeConfig(orig);
            }

            public Builder name(String name) {
                target.name = name;
                return this;
            }

            public Builder className(String name) {
                target.className = name;
                return this;
            }

             public Builder addParam(String name, String value) {
                target.params.put(name, value);
                return this;
            }

            public Builder addParams(Map<String,String> params) {
                target.params.putAll(params);
                return this;
            }

            public Builder defaultResultParam(String defaultResultParam) {
                target.defaultResultParam = defaultResultParam;
                return this;
            }

            public Builder location(Location loc) {
                target.location = loc;
                return this;
            }

            public ResultTypeConfig build() {
                ResultTypeConfig result = target;
                target = new ResultTypeConfig(target);
                return result;
            }
        }

}
