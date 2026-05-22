public class IssueResolutionAcceptor {

	private List<IssueResolution> issueResolutions = Lists.newArrayList();

	private IssueModificationContext.Factory modificationContextFactory;

	@Inject
	public IssueResolutionAcceptor(IssueModificationContext.Factory modificationContextFactory) {
		this.modificationContextFactory = modificationContextFactory;
	}

	public void accept(Issue issue, String label, String description, String image, IModification modification) {
		issueResolutions.add(new IssueResolution(label, description, image, modificationContextFactory.createModificationContext(issue),
				modification));
	}

	public void accept(Issue issue, String label, String description, String image, ISemanticModification semanticModification) {
		SemanticModificationWrapper modificationWrapper = new SemanticModificationWrapper(issue.getUriToProblem(), semanticModification);
		issueResolutions.add(new IssueResolution(label, description, image, modificationContextFactory.createModificationContext(issue),
				modificationWrapper));
	}
	
	/**
	 * @since 2.4
	 */
	public void accept(Issue issue, String label, String description, String image, IModification modification, int relevance) {
		issueResolutions.add(new IssueResolution(label, description, image, modificationContextFactory.createModificationContext(issue),
				modification, relevance));
	}
	
	/**
	 * @since 2.4
	 */
	public void accept(Issue issue, String label, String description, String image, ISemanticModification semanticModification, int relevance) {
		SemanticModificationWrapper modificationWrapper = new SemanticModificationWrapper(issue.getUriToProblem(), semanticModification);
		issueResolutions.add(new IssueResolution(label, description, image, modificationContextFactory.createModificationContext(issue),
				modificationWrapper, relevance));
	}

	/**
	 * Use as for a multi-quickfix methods.<br>
	 * This method will be called multiple times if more than one issue was selected to fix.
	 * 
	 * @see IMultiModification
	 * 
	 * @since 2.13
	 */
	public <T extends EObject> void acceptMulti(Issue issue, String label, String description, String image,
			IMultiModification<T> modification) {
		acceptMulti(issue, label, description, image, modification, 0);
	}
	
	/**
	 * Use as for a multi-quickfix methods.<br>
	 * This method will be called multiple times if more than one issue was selected to fix.
	 * 
	 * @see IMultiModification
	 * 
	 * @since 2.13
	 */
	public <T extends EObject> void acceptMulti(Issue issue, String label, String description, String image,
			IMultiModification<T> modification, int relevance) {
		MultiModificationWrapper wrapper = new MultiModificationWrapper(issue, modification);
		issueResolutions.add(new IssueResolution(label, description, image, modificationContextFactory.createModificationContext(issue), wrapper, relevance));
	}

	/**
	 * Use as for a multi-quickfix methods.<br>
	 * This method will be called multiple times if more than one issue was selected to fix.
	 * 
	 * @see ICompositeModification
	 * 
	 * @since 2.13
	 */
	public  <T extends EObject> void acceptMulti(Issue issue, String label, String description, String image, ICompositeModification<T> modification) {
		acceptMulti(issue, label, description, image, modification, 0);
	}

	/**
	 * Use as for a multi-quickfix methods.<br>
	 * This method will be called multiple times if more than one issue was selected to fix.
	 * 
	 * @see ICompositeModification
	 * 
	 * @since 2.13
	 */
	public <T extends EObject> void acceptMulti(Issue issue, String label, String description, String image, ICompositeModification<T> modification,
			int relevance) {
		CompositeModificationWrapper wrapper = new CompositeModificationWrapper(issue, modification);
		issueResolutions.add(new IssueResolution(label, description, image, modificationContextFactory.createModificationContext(issue), wrapper, relevance));
	}

	public List<IssueResolution> getIssueResolutions() {
		return issueResolutions;
	}

}
