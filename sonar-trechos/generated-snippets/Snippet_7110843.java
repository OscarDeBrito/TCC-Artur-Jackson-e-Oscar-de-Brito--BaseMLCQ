@BuiltInProcessor("PageViewTelemetryFilter")
public final class PageViewTelemetryFilter implements TelemetryProcessor {

    private long durationThresholdInMS = 0l;
    private final Set<String> notNeededUrls = new HashSet<String>();
    private final Set<String> notNeededNames = new HashSet<String>();

    public PageViewTelemetryFilter() {
    }

    @Override
    public boolean process(Telemetry telemetry) {
        if (telemetry == null) {
            return true;
        }

        if (!(telemetry instanceof PageViewTelemetry)) {
            return true;
        }

        PageViewTelemetry asPVT = (PageViewTelemetry) telemetry;
        URI uri = asPVT.getUri();
        if (uri == null) {
            return true;
        } else {
            String uriPath = uri.toString();
            for (String notNeededUri : notNeededUrls) {
                if (uriPath.contains(notNeededUri)) {
                    return false;
                }
            }
        }

        if (notNeededNames.contains(asPVT.getName())) {
            return false;
        }

        long pvtDuration = asPVT.getDuration();
        if (durationThresholdInMS <= pvtDuration) {
            return true;
        }

        return false;
    }

    public void setDurationThresholdInMS(String durationThresholdInMS) throws NumberFormatException {
        try {
            this.durationThresholdInMS = Long.valueOf(durationThresholdInMS);
            InternalLogger.INSTANCE.trace("PageViewTelemetryFilter: successfully set DurationThresholdInMS to %s", durationThresholdInMS);
        } catch (NumberFormatException e) {
            InternalLogger.INSTANCE.error("PageViewTelemetryFilter: failed to set DurationThresholdInMS:%s Exception : %s ",
                    durationThresholdInMS, ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    public void setNotNeededNames(String notNeededNames) throws Throwable {
        try {
            List<String> notNeededAsList = Arrays.asList(notNeededNames.split(","));
            for (String notNeeded : notNeededAsList) {
                String ready = notNeeded.trim();
                if (LocalStringsUtils.isNullOrEmpty(ready)) {
                    continue;
                }

                this.notNeededNames.add(ready);
            }

            InternalLogger.INSTANCE.trace(String.format("PageViewTelemetryFilter: set NotNeededNames: %s", notNeededNames));
        } catch (ThreadDeath td) {
            throw td;
        } catch (Throwable t) {
            try {
                InternalLogger.INSTANCE.trace("PageViewTelemetryFilter: failed to parse NotNeededNames: %s Exception : %s", notNeededNames,
                        ExceptionUtils.getStackTrace(t));
            } catch (ThreadDeath td) {
                throw td;
            } catch (Throwable t2) {
                // chomp
            }
            throw t;
        }
    }

    public void setNotNeededUrls(String notNeededUrls) throws Throwable {
        try {
            List<String> notNeededAsList = Arrays.asList(notNeededUrls.split(","));
            for (String notNeeded : notNeededAsList) {
                String ready = notNeeded.trim();
                if (LocalStringsUtils.isNullOrEmpty(ready)) {
                    continue;
                }

                this.notNeededUrls.add(ready);
            }
            InternalLogger.INSTANCE.trace("PageViewTelemetryFilter: set %s", notNeededUrls);
        } catch (ThreadDeath td) {
            throw td;
        } catch (Throwable t) {
            try {
                InternalLogger.INSTANCE.error("PageViewTelemetryFilter: failed to parse NotNeededUrls: %s Exception : %s", notNeededUrls,
                        ExceptionUtils.getStackTrace(t));
            } catch (ThreadDeath td) {
                throw td;
            } catch (Throwable t2) {
                // chomp
            }
            throw t;
        }
    }
}
