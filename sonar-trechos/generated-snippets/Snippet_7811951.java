public class Snippet__7811951 {

    @Override
      public void setIgnoreDepartedMembers(boolean ignore) {
        this.ignoreDepartedMembers = ignore;
        if (ignore) {
          setWaitOnExceptionFlag(true);
        }
      }

}
