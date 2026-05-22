public class Snippet__9249010 {

    @SuppressWarnings("serial") // JDK implementation class
    public class VmEvent extends EventObject {

        /**
         * Construct a new VmEvent instance.
         *
         * @param vm the MonitoredVm source of the event.
         */
        public VmEvent(MonitoredVm vm) {
            super(vm);
        }

        /**
         * Return the MonitoredVm source of this event.
         *
         * @return MonitoredVm - the source of this event.
         */
        public MonitoredVm getMonitoredVm() {
          return (MonitoredVm)source;
        }
    }

}
