public class Snippet__4534763 {

    protected void warn(String msg, Object ... params) {
    	if (LOG_LEVEL >= LogService.LOG_WARNING) {
    	    log(LogService.LOG_WARNING, params.length > 0 ? String.format(msg, params) : msg);
    	}
        }

}
