public abstract class Event<L> {

    private List<L> listener;
    private boolean fireEvents;
    private boolean eventWasFired;

    public Event() {
        listener = new ArrayList<>();
        fireEvents = true;
    }

    public void addListener(L l) {
        listener.add(l);
    }

    /**
     * Remove listener
     * @param l
     */
    public void removeListener(final L l) {
        listener.remove(l);
    }

    public void fire() {
        if(fireEvents) {
            List<L> tmpList = new ArrayList<>(listener);
            for (L l : tmpList) {
                fire(l);
            }
        } else {
            eventWasFired = true;
        }
    }

    public void beginAtomic() {
        assert fireEvents : "endAtomic has to be called before another beginAtomic may be called";
        this.fireEvents = false;
        this.eventWasFired = false;
    }

    public void endAtomic() {
        assert !fireEvents : "beginAtomic has to be called first";
        this.fireEvents = true;
        if(eventWasFired) {
            fire();
        }
    }

    protected abstract void fire(L l);
}
