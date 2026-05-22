public class Snippet__5857311 {

    static private String getText(String text) {
        int start = text.indexOf("<TEXT>");
        int end = text.indexOf("</TEXT>");
        if (start != -1 && end != -1) {
          return text.substring(start + 6, end);
        } else {
          return text;
        }
      }

}
