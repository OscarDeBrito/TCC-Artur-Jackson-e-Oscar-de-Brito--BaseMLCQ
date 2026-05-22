public class Snippet__7879764 {

    public static ImmutableMap<String, String> readClassMapping(Iterable<String> lines) {
        ImmutableMap.Builder<String, String> classMappingBuilder = ImmutableMap.builder();

        for (String line : lines) {
          if (line.charAt(0) == ' ') {
            // This is a member mapping, which we don't handle yet.
            continue;
          }

          Matcher matcher = CLASS_LINE_PATTERN.matcher(line);
          if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid line in proguard mapping: " + line);
          }

          classMappingBuilder.put(matcher.group(1), matcher.group(2));
        }

        return classMappingBuilder.build();
      }

}
