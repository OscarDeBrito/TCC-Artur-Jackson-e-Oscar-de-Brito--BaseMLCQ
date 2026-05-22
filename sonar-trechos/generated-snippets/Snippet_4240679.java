public abstract class NumericRange<T extends Number & Comparable<T>> extends AbstractRange<T, T> {

    /**
     * Construct a new {@link NumericRange}.
     * @param leftEndpoint left endpoint
     * @param rightEndpoint right endpoint
     * @param step increment step
     * @param nextValue function to implement the taking of a step
     */
    protected NumericRange(Endpoint<T> leftEndpoint, Endpoint<T> rightEndpoint, T step,
            BinaryFunction<T, T, T> nextValue) {
        super(leftEndpoint, rightEndpoint, step, nextValue);
    }

    /**
     * {@inheritDoc}
     */
    public boolean contains(T obj) {
        if (obj == null) {
            return false;
        }
        double leftValue = this.getLeftEndpoint().getValue().doubleValue();
        double rightValue = this.getRightEndpoint().getValue().doubleValue();
        boolean includeLeft = this.getLeftEndpoint().getBoundType() == BoundType.CLOSED;
        boolean includeRight = this.getRightEndpoint().getBoundType() == BoundType.CLOSED;
        double step = this.getStep().doubleValue();
        double value = obj.doubleValue();

        double firstValue = 0;
        double lastValue = 0;

        if (step < 0.0) {
            firstValue = includeLeft ? leftValue : leftValue + step;
            lastValue = includeRight ? rightValue : Math.nextUp(rightValue);
            if (value > firstValue || value < lastValue) {
                return false;
            }
        } else {
            firstValue = includeLeft ? leftValue : leftValue + step;
            lastValue = includeRight ? rightValue : rightValue
                                                    - (rightValue - Math
                                                        .nextUp(rightValue));
            if (value < firstValue || value > lastValue) {
                return false;
            }
        }
        return ((value - firstValue) / step + 1) % 1.0 == 0.0;
    }

}
