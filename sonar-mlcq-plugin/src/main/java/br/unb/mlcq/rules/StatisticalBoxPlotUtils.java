package br.unb.mlcq.rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class StatisticalBoxPlotUtils {

    public static final double TUKEY = 1.5;
    public static final double FUZZINESS_PERCENT = 8.0;

    private StatisticalBoxPlotUtils() {
    }

    public static BoxPlotStats computeStats(List<Integer> rawValues) {
        if (rawValues == null || rawValues.isEmpty()) {
            return new BoxPlotStats(
                    Collections.emptyList(),
                    0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
            );
        }

        List<Double> values = new ArrayList<>();
        for (Integer v : rawValues) {
            if (v != null) {
                values.add(v.doubleValue());
            }
        }

        Collections.sort(values);

        double q1 = getLowerQuartile(values);
        double q3 = getUpperQuartile(values);
        double iqr = q3 - q1;
        double minBound = Math.max(0.0, q1 - TUKEY * iqr);
        double maxBound = q3 + TUKEY * iqr;
        double range = maxBound - minBound;
        double fuzziness = FUZZINESS_PERCENT * range / 100.0;

        return new BoxPlotStats(values, q1, q3, iqr, minBound, maxBound, fuzziness, getMedian(values));
    }

    public static boolean isHighValue(double loc, BoxPlotStats stats) {
        return loc > (stats.getQ3() - stats.getFuzziness())
                && loc < (stats.getMaxBound() + stats.getFuzziness());
    }

    public static double getMedian(List<Double> values) {
        return getPercentile(values, 0.5);
    }

    public static double getPercentile(List<Double> values, double p) {
        int n = values.size();
        if (n == 0) {
            return 0.0;
        }

        double a = (n + 1) * p;
        int j = (int) a;
        double g = a - j;

        if (j == n) {
            return (1 - g) * values.get(j - 1) + g * values.get(j - 1);
        } else if (j == 0) {
            return (1 - g) * values.get(0) + g * values.get(0);
        } else {
            return (1 - g) * values.get(j - 1) + g * values.get(j);
        }
    }

    public static double getLowerQuartile(List<Double> values) {
        int n = values.size();
        if (n == 0) {
            return 0.0;
        }
        int lowerQuartile = (n + 1) / 4;
        lowerQuartile = Math.min(lowerQuartile, values.size() - 1);
        return values.get(lowerQuartile);
    }

    public static double getUpperQuartile(List<Double> values) {
        int n = values.size();
        if (n == 0) {
            return 0.0;
        }
        int upperQuartile = (3 * n + 3) / 4;
        upperQuartile = Math.min(upperQuartile, values.size() - 1);
        return values.get(upperQuartile);
    }

    public static final class BoxPlotStats {
        private final List<Double> sortedValues;
        private final double q1;
        private final double q3;
        private final double iqr;
        private final double minBound;
        private final double maxBound;
        private final double fuzziness;
        private final double median;

        public BoxPlotStats(
                List<Double> sortedValues,
                double q1,
                double q3,
                double iqr,
                double minBound,
                double maxBound,
                double fuzziness,
                double median
        ) {
            this.sortedValues = sortedValues;
            this.q1 = q1;
            this.q3 = q3;
            this.iqr = iqr;
            this.minBound = minBound;
            this.maxBound = maxBound;
            this.fuzziness = fuzziness;
            this.median = median;
        }

        public List<Double> getSortedValues() {
            return sortedValues;
        }

        public double getQ1() {
            return q1;
        }

        public double getQ3() {
            return q3;
        }

        public double getIqr() {
            return iqr;
        }

        public double getMinBound() {
            return minBound;
        }

        public double getMaxBound() {
            return maxBound;
        }

        public double getFuzziness() {
            return fuzziness;
        }

        public double getMedian() {
            return median;
        }
    }
}
