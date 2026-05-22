public class Snippet__7659358 {

    protected void notifyStateChanged(StateMachineContext<S, E> context) {
    		if (log.isTraceEnabled()) {
    			log.trace("Notify notifyStateChanged " + context);
    		}
    		ensembleListener.stateChanged(context);
    	}

}
