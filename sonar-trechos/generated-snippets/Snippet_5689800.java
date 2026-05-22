public class StrutsActionProxy extends DefaultActionProxy {

    private static final long serialVersionUID = -2434901249671934080L;

    public StrutsActionProxy(ActionInvocation inv, String namespace, String actionName, String methodName,
                             boolean executeResult, boolean cleanupContext) {
        super(inv, namespace, actionName, methodName, executeResult, cleanupContext);
    }

    public String execute() throws Exception {
        ActionContext previous = ActionContext.getContext();
        ActionContext.setContext(invocation.getInvocationContext());
        try {
// This is for the new API:
//            return RequestContextImpl.callInContext(invocation, new Callable<String>() {
//                public String call() throws Exception {
//                    return invocation.invoke();
//                }
//            });

            return invocation.invoke();
        } finally {
            if (cleanupContext)
                ActionContext.setContext(previous);
        }
    }

    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String getErrorMessage() {
        if ((namespace != null) && (namespace.trim().length() > 0)) {
            String contextPath = ServletActionContext.getRequest().getContextPath();
            return localizedTextProvider.findDefaultText(
                    "struts.exception.missing-package-action.with-context",
                    Locale.getDefault(),
                    new String[]{namespace, actionName, contextPath}
            );
        } else {
            return super.getErrorMessage();
        }
    }

}
