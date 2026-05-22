public class Snippet__7375581 {

    private LogLevel coerceLogLevel(String level) {
    		String trimmedLevel = level.trim();
    		if ("false".equalsIgnoreCase(trimmedLevel)) {
    			return LogLevel.OFF;
    		}
    		return LogLevel.valueOf(trimmedLevel.toUpperCase(Locale.ENGLISH));
    	}

}
