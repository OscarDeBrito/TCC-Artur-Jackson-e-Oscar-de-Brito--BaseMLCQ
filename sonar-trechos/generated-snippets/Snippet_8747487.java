public class Snippet__8747487 {

    static String classNameBase(String className) {
          String[] names = className.split("\\.", -1);
          if (names == null || names.length == 0) {
            return className;
          }
          return names[names.length-1];
        }

}
