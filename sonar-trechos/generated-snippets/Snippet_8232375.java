public abstract class LayoutEvent implements Event {

    protected Layout target;

    public LayoutEvent(Layout target) {
        this.target = target;
    }

    /**
     * The target layout object.
     */
    public Layout getTarget() {
        return this.target;
    }
}
