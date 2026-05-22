public class Snippet__7824232 {

    public static DataPolicy fromString(String s) {
        String[] allowedValues =
            new String[] {"EMPTY", "NORMAL", "REPLICATE", "PERSISTENT_REPLICATE", "PARTITION",
                "PRELOADED", "PERSISTENT_PARTITION"};
        int valueIndex = -1;
        for (int i = 0; i < allowedValues.length; i++) {
          if (allowedValues[i].equals(s)) {
            valueIndex = i;
            break;
          }
        }

        if (valueIndex != -1)
          return VALUES[valueIndex];

        return null;
      }

}
