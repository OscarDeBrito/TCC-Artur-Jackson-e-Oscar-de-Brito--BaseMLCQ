public class Snippet__7274045 {

    public void propertyChange(PropertyChangeEvent evt) {
                if (!evt.getNewValue().equals(Stateful.STATE_AVAILABLE)) {
                    ((Application)evt.getSource()).removePropertyChangeListener(
                            Stateful.PROPERTY_STATE, this);
                    client.disconnectImpl(false);
                    availabilityListener = null;
                }
            }

}
