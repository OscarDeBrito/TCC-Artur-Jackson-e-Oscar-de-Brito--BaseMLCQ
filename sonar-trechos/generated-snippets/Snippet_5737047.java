public class Snippet__5737047 {

    public SimpleFileNameFormat withTimeFormat(String timeFormat) {
            //check format
            try {
                new SimpleDateFormat(timeFormat);
            } catch (Exception e) {
                throw new IllegalArgumentException("invalid timeFormat: " + e.getMessage());
            }
            this.timeFormat = timeFormat;
            return this;
        }

}
