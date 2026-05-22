public class Snippet__6923759 {

    public static String joinLongs(long[] tokens, String delimiter) {
        if (tokens == null) return "";
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < tokens.length; i++) {
          if (i > 0 && delimiter != null) {
            result.append(delimiter);
          }
          result.append(String.valueOf(tokens[i]));
        }
        return result.toString();
      }

}
