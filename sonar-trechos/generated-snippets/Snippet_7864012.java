public class Snippet__7864012 {

    public static EvictionAction parseAction(String s) {
        if (s == null)
          return NONE;
        if (s.length() < 1)
          return NONE;
        for (int i = 0; i < stringTable.length; ++i) {
          if (s.equals(stringTable[i])) {
            return enumValueTable[i];
          }
        }
        return NONE;
      }

}
