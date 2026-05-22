public class Snippet__6016685 {

    @JsonCreator
        public static TlsConfiguration from(String s) {
            switch (s.toLowerCase()) {
                case "none":
                    return NONE;
                case "simple":
                    return SIMPLE;
                case "external":
                    return EXTERNAL;
                default:
                    throw new IllegalArgumentException(String.format("Unknown TLS Configuration type: %s", s));
            }
        }

}
