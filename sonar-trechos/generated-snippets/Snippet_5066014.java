public class Snippet__5066014 {

    public Snippet__5066014(final Locale locale, final String messagePattern, final Object... arguments) {
            this.locale = locale;
            this.messagePattern = messagePattern;
            this.argArray = arguments;
            if (arguments != null && arguments.length > 0 && arguments[arguments.length - 1] instanceof Throwable) {
                this.throwable = (Throwable) arguments[arguments.length - 1];
            }
        }

}
