public class Snippet__7761185 {

    @Override
      public int hashCode()
      {
        int result = name.hashCode();
        result = 31 * result + field.hashCode();
        result = 31 * result + (errorBoundsStdDev != null ? errorBoundsStdDev.hashCode() : 0);
        return result;
      }

}
