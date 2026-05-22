public class Snippet__7072307 {

    private static final class DefaultLoggerProxy extends LoggerProxy {
            /**
             * Default platform logging support - output messages to System.err -
             * equivalent to ConsoleHandler with SimpleFormatter.
             */
            private static PrintStream outputStream() {
                return System.err;
            }

            volatile Level effectiveLevel; // effective level (never null)
            volatile Level level;          // current level set for this node (may be null)

            DefaultLoggerProxy(String name) {
                super(name);
                this.effectiveLevel = deriveEffectiveLevel(null);
                this.level = null;
            }

            boolean isEnabled() {
                return effectiveLevel != Level.OFF;
            }

            Level getLevel() {
                return level;
            }

            void setLevel(Level newLevel) {
                Level oldLevel = level;
                if (oldLevel != newLevel) {
                    level = newLevel;
                    effectiveLevel = deriveEffectiveLevel(newLevel);
                }
            }

            void doLog(Level level, String msg) {
                if (isLoggable(level)) {
                    outputStream().print(format(level, msg, null));
                }
            }

            void doLog(Level level, String msg, Throwable thrown) {
                if (isLoggable(level)) {
                    outputStream().print(format(level, msg, thrown));
                }
            }

            void doLog(Level level, String msg, Object... params) {
                if (isLoggable(level)) {
                    String newMsg = formatMessage(msg, params);
                    outputStream().print(format(level, newMsg, null));
                }
            }

            boolean isLoggable(Level level) {
                Level effectiveLevel = this.effectiveLevel;
                return level.intValue() >= effectiveLevel.intValue() && effectiveLevel != Level.OFF;
            }

            // derive effective level (could do inheritance search like j.u.l.Logger)
            private Level deriveEffectiveLevel(Level level) {
                return level == null ? DEFAULT_LEVEL : level;
            }

            // Copied from java.util.logging.Formatter.formatMessage
            private String formatMessage(String format, Object... parameters) {
                // Do the formatting.
                try {
                    if (parameters == null || parameters.length == 0) {
                        // No parameters.  Just return format string.
                        return format;
                    }
                    // Is it a java.text style format?
                    // Ideally we could match with
                    // Pattern.compile("\\{\\d").matcher(format).find())
                    // However the cost is 14% higher, so we cheaply check for
                    // 1 of the first 4 parameters
                    if (format.indexOf("{0") >= 0 || format.indexOf("{1") >=0 ||
                                format.indexOf("{2") >=0|| format.indexOf("{3") >=0) {
                        return java.text.MessageFormat.format(format, parameters);
                    }
                    return format;
                } catch (Exception ex) {
                    // Formatting failed: use format string.
                    return format;
                }
            }

            private static final String formatString =
                LoggingSupport.getSimpleFormat(false); // don't check logging.properties

            // minimize memory allocation
            private Date date = new Date();
            private synchronized String format(Level level, String msg, Throwable thrown) {
                date.setTime(System.currentTimeMillis());
                String throwable = "";
                if (thrown != null) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    pw.println();
                    thrown.printStackTrace(pw);
                    pw.close();
                    throwable = sw.toString();
                }

                return String.format(formatString,
                                     date,
                                     getCallerInfo(),
                                     name,
                                     level.name(),
                                     msg,
                                     throwable);
            }

            // Returns the caller's class and method's name; best effort
            // if cannot infer, return the logger's name.
            private String getCallerInfo() {
                String sourceClassName = null;
                String sourceMethodName = null;

                Throwable throwable = new Throwable();

                String logClassName = "sun.util.logging.PlatformLogger";
                boolean lookingForLogger = true;
                for (StackTraceElement frame : throwable.getStackTrace()) {
                    String cname = frame.getClassName();
                    if (lookingForLogger) {
                        // Skip all frames until we have found the first logger frame.
                        if (cname.equals(logClassName)) {
                            lookingForLogger = false;
                        }
                    } else {
                        if (!cname.equals(logClassName)) {
                            // We've found the relevant frame.
                            sourceClassName = cname;
                            sourceMethodName = frame.getMethodName();
                            break;
                        }
                    }
                }

                if (sourceClassName != null) {
                    return sourceClassName + " " + sourceMethodName;
                } else {
                    return name;
                }
            }
        }

}
