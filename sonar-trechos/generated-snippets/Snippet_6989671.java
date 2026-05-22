public class Snippet__6989671 {

    public static String generateSourceMaps(
          Type type, Map<SourcePosition, SourcePosition> javaSourcePositionByOutputSourcePosition)
          throws IOException {
        return renderSourceMapToString(type, javaSourcePositionByOutputSourcePosition);
      }

}
