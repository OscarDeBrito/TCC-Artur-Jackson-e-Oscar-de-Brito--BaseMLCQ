public class BeanMethodContentAssistCalculator extends
		WebflowActionMethodContentAssistCalculator {

	private static final String EVENT_CLASS = 
		"org.springframework.webflow.execution.Event";

	private static final String REQUEST_CONTEXT_CLASS = 
		"org.springframework.webflow.execution.RequestContext";

	public BeanMethodContentAssistCalculator() {
		super(new FlagsMethodFilter(FlagsMethodFilter.PUBLIC
				| FlagsMethodFilter.NOT_INTERFACE
				| FlagsMethodFilter.NOT_CONSTRUCTOR, EVENT_CLASS,
				new String[] { REQUEST_CONTEXT_CLASS }));
	}
}
