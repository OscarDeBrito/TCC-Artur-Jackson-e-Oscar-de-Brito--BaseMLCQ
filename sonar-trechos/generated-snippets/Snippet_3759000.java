public abstract class BaseWindowConfig implements WindowConfig {
    protected final int windowLength;
    protected final int slideLength;

    protected BaseWindowConfig(int windowLength, int slideLength) {
        this.windowLength = windowLength;
        this.slideLength = slideLength;
    }

    @Override
    public int getWindowLength() {
        return windowLength;
    }

    @Override
    public int getSlidingLength() {
        return slideLength;
    }

    public void validate() {
        if (slideLength > windowLength) {
            throw new IllegalArgumentException("slideLength '" + slideLength +
                    "' should always be less than windowLegth '" + windowLength + "'");
        }
    }
}
