public class Snippet__7727470 {

    public Snippet__7727470(MetricSearch metricSearch, MetricValidator metricValidator,
                             boolean redirectHostMetrics, String hostMetricDir, String hostPostfixes) {
            this.metricSearch = metricSearch;
            this.metricValidator = metricValidator;
            this.redirectHostMetrics = redirectHostMetrics;
            this.hostMetricDir = hostMetricDir;
            this.hostPostfixes = Splitter.on(',').omitEmptyStrings().splitToList(hostPostfixes);
            if (redirectHostMetrics) {
                log.info("Host host metrics redirection enabled for postfixes {} to dir {}", hostPostfixes, hostMetricDir);
            } else {
                log.info("Host metric redirection disabled");
            }
        }

}
