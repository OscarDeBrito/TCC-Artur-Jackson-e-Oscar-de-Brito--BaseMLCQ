private class JavaThreadsToolBar extends CommonToolBar {
        public JavaThreadsToolBar(StatusBar status) {
            super(HSDBActionManager.getInstance(), status);
        }

        protected void addComponents() {
            addButton(manager.getAction(InspectAction.VALUE_COMMAND));
            addButton(manager.getAction(MemoryAction.VALUE_COMMAND));
            addButton(manager.getAction(JavaStackTraceAction.VALUE_COMMAND));

            addToggleButton(manager.getStateChangeAction(ThreadInfoAction.VALUE_COMMAND));
            addButton(manager.getAction(FindCrashesAction.VALUE_COMMAND));
        }
    }
