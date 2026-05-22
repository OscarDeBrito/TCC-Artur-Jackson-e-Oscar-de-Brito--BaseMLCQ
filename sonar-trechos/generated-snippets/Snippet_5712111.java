public class SimpleVersion implements Comparable<SimpleVersion> {
    private static final Pattern VERSION_PATTERN = Pattern.compile("^(\\d+)[\\.\\-\\_]+(\\d+).*$");
    private final int _major;
    private final int _minor;

    public SimpleVersion(String version) {
        Matcher m = VERSION_PATTERN.matcher(version);
        int maj = -1;
        int min = -1;
        if (!m.matches()) {
            //Unknown should only happen during compilation or some unit tests.
            if (!"Unknown".equals(version)) {
                throw new IllegalArgumentException("Cannot parse '" + version + "'");
            }
        } else {
            maj = Integer.valueOf(m.group(1));
            min = Integer.valueOf(m.group(2));
        }
        _major = maj;
        _minor = min;
    }

    public int getMajor() {
        return _major;
    }

    public int getMinor() {
        return _minor;
    }

    @Override
    public int hashCode() {
        return (Integer.hashCode(_major) * 17) & Integer.hashCode(_minor);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof SimpleVersion)) {
            return false;
        }

        return compareTo((SimpleVersion) o) == 0;
    }

    @Override
    public int compareTo(SimpleVersion o) {
        int ret = Integer.compare(_major, o._major);
        if (ret == 0) {
            ret = Integer.compare(_minor, o._minor);
        }
        return ret;
    }

    @Override
    public String toString() {
        return _major + "." + _minor;
    }
}
