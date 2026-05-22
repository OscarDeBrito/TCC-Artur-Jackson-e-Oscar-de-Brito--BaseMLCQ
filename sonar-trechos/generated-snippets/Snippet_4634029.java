public class Snippet__4634029 {

    public int hashCode() {
        int result = cookie.hashCode();

        for (String file : files) {
          result = PRIME * result + file.hashCode();
        }

        return result;
      }

}
