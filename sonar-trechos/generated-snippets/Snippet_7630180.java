public class FocusedBeansStructuredTextViewerConfiguration extends
		BeansStructuredTextViewerConfiguration {

	@Override
	public IContentAssistProcessor[] getContentAssistProcessors(
			ISourceViewer sourceViewer, String partitionType) {

		IContentAssistProcessor[] processors = super
				.getContentAssistProcessors(sourceViewer, partitionType);
		if (processors != null) {
			IContentAssistProcessor[] wrappedProcessors = 
				new IContentAssistProcessor[processors.length];
			for (int i = 0; i < processors.length; i++) {
				wrappedProcessors[i] = new FocusedBeansContentAssistProcessor(
						processors[i]);
			}
			return wrappedProcessors;
		}
		return processors;
	}
}
