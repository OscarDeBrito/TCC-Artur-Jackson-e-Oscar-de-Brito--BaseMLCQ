public class Snippet__9213827 {

    public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("java.util.regex.Matcher")
                    .append("[pattern=").append(pattern())
                    .append(" region=")
                    .append(regionStart()).append(',').append(regionEnd())
                    .append(" lastmatch=");
            if ((first >= 0) && (group() != null)) {
                sb.append(group());
            }
            sb.append(']');
            return sb.toString();
        }

}
