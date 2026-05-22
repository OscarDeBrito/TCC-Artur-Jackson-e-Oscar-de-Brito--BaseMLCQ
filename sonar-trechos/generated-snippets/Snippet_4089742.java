public class Snippet__4089742 {

    private static String extractErrorMessage(Throwable e) {
            Throwable cause = getRootCause(e);
            String fullyQualifiedExceptionClassName = cause.getClass().getName();
            String[] hierarchySplits = fullyQualifiedExceptionClassName.split("\\.");
            //try returning the class without package qualification
            String exceptionClassName = hierarchySplits[hierarchySplits.length - 1];
            String localizedMessage = cause.getLocalizedMessage();
            if (localizedMessage == null) {
                localizedMessage = "Internal error. Please check instance logs for further details.";
            }
            return localizedMessage + " [" + exceptionClassName + "]";
        }

}
