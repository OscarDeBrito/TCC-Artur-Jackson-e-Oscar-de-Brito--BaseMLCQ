public class Snippet__3933339 {

    private final void writePreInvoke() {
        //The place to go if the listener is null
        Label nullListener = newLabel();
        beginListenerInvocation(nullListener);

        // The listener is on the stack, we need (target, method, args)
    
        loadLocal(dispatchTarget);
        getStatic(typeBeingWoven, methodStaticFieldName, METHOD_TYPE);
        loadArgArray();
    
        //invoke it and store the token returned
        invokeInterface(LISTENER_TYPE, PRE_INVOKE_METHOD);
        storeLocal(preInvokeReturnedToken);
    
        mark(nullListener);
      }

}
