public class Snippet__6177738 {

    @Override
      public boolean equals(Object obj) {
        if (this == obj) {
          return true;
        }
        if (!(obj instanceof StatusMessage)) {
          return false;
        }
        StatusMessage that = (StatusMessage) obj;

        return Objects.equals(message, that.message)
            && Objects.equals(type, that.type)
            && isAccessible == that.isAccessible;
      }

}
