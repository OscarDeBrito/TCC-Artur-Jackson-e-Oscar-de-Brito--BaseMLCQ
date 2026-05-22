public static class Builder implements Interceptor.Builder {

    private Pattern regex;
    private boolean excludeEvents;

    @Override
    public void configure(Context context) {
      String regexString = context.getString(REGEX, DEFAULT_REGEX);
      regex = Pattern.compile(regexString);
      excludeEvents = context.getBoolean(EXCLUDE_EVENTS,
          DEFAULT_EXCLUDE_EVENTS);
    }

    @Override
    public Interceptor build() {
      logger.info(String.format(
          "Creating RegexFilteringInterceptor: regex=%s,excludeEvents=%s",
          regex, excludeEvents));
      return new RegexFilteringInterceptor(regex, excludeEvents);
    }
  }
