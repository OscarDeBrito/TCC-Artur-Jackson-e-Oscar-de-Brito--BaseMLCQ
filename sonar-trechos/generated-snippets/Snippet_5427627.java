public interface WindowActionMappingListener {
    /**
     * Window action mapping listeners.
     */
    public static class Listeners extends ListenerList<WindowActionMappingListener>
        implements WindowActionMappingListener {
        @Override
        public void actionMappingAdded(Window window) {
            forEach(listener -> listener.actionMappingAdded(window));
        }

        @Override
        public void actionMappingsRemoved(Window window, int index,
            Sequence<Window.ActionMapping> removed) {
            forEach(listener -> listener.actionMappingsRemoved(window, index, removed));
        }

        @Override
        public void keyStrokeChanged(Window.ActionMapping actionMapping,
            Keyboard.KeyStroke previousKeyStroke) {
            forEach(listener -> listener.keyStrokeChanged(actionMapping, previousKeyStroke));
        }

        @Override
        public void actionChanged(Window.ActionMapping actionMapping, Action previousAction) {
            forEach(listener -> listener.actionChanged(actionMapping, previousAction));
        }
    }

    /**
     * Called when an action mapping has been added to a window.
     *
     * @param window The source of this event.
     */
    public void actionMappingAdded(Window window);

    /**
     * Called when action mappings have been removed from a window.
     *
     * @param window  The window that is affected.
     * @param index   Starting index of the action mappings that were removed.
     * @param removed The sequence of action mappings that were removed.
     */
    public void actionMappingsRemoved(Window window, int index,
        Sequence<Window.ActionMapping> removed);

    /**
     * Called when an action mapping's keystroke has changed.
     *
     * @param actionMapping     The action mapping that has changed.
     * @param previousKeyStroke The previous keystroke (if any) associated with this mapping.
     */
    public void keyStrokeChanged(Window.ActionMapping actionMapping,
        Keyboard.KeyStroke previousKeyStroke);

    /**
     * Called when an action mapping's action has changed.
     *
     * @param actionMapping  The action mapping that has changed.
     * @param previousAction The action previously associated with this mapping.
     */
    public void actionChanged(Window.ActionMapping actionMapping, Action previousAction);
}
