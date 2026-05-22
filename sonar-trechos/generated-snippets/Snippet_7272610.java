public class Snippet__7272610 {

    public String formatValue(long value, int format) {
                switch (format) {
                    case FORMAT_TOOLTIP:
                    case FORMAT_UNITS:
                        return PERCENT_FORMAT.format(value / factor);
                    case FORMAT_DETAILS:
                    case FORMAT_EXPORT:
                        return NUMBER_FORMAT.format(value * 100 / factor);
                    default:
                        return null;
                }
            }

}
