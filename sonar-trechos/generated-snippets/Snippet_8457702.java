public class Snippet__8457702 {

    public static void changeFunctionEndToken(String... endToken) {
            for (SimpleTokenType type : KNOWN_TOKENS) {
                if (type.getType() == TokenType.functionEnd) {
                    KNOWN_TOKENS.remove(type);
                }
            }

            // add after the start tokens
            int pos = 0;
            for (SimpleTokenType type : KNOWN_TOKENS) {
                if (type.getType() == TokenType.functionStart) {
                    pos++;
                }
            }

            // add after function start of list as its a more common token to be used
            for (String token : endToken) {
                KNOWN_TOKENS.add(pos, new SimpleTokenType(TokenType.functionEnd, token));
            }
        }

}
