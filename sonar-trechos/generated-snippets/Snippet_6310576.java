public class Snippet__6310576 {

    public boolean isPermissionErrorMessage(Throwable caught) {
            if ((caught instanceof GwtKapuaException)
                    && GwtKapuaErrorCode.SUBJECT_UNAUTHORIZED.equals(((GwtKapuaException) caught).getCode())) {
                exitMessage = caught.getLocalizedMessage();
                return true;
            } else {
                return false;
            }
        }

}
