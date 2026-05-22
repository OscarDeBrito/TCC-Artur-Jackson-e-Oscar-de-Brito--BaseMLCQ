public class Snippet__5345695 {

    private static final class SimpleNameParser implements NameParser {

            private static final Properties PARSER_PROPERTIES = new Properties();

            static {
                PARSER_PROPERTIES.put("jndi.syntax.direction", "left_to_right");
                PARSER_PROPERTIES.put("jndi.syntax.separator", "/");
            }

            private SimpleNameParser() {
            }

            @Override
            public Name parse(final String name) throws NamingException {
                return new CompoundName(name, PARSER_PROPERTIES);
            }
        }

}
