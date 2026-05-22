public class ContextAwareFactory implements RepositoryFactory {

	/*-----------*
	 * Constants *
	 *-----------*/

	/**
	 * The type of repositories that are created by this factory.
	 * 
	 * @see RepositoryFactory#getRepositoryType()
	 */
	public static final String REPOSITORY_TYPE = "openrdf:ContextAwareRepository";

	/*---------*
	 * Methods *
	 *---------*/

	/**
	 * Returns the repository's type: <tt>openrdf:ContextAwareRepository</tt>.
	 */
	@Override
	public String getRepositoryType() {
		return REPOSITORY_TYPE;
	}

	@Override
	public RepositoryImplConfig getConfig() {
		return new ContextAwareConfig();
	}

	@Override
	public Repository getRepository(RepositoryImplConfig configuration) throws RepositoryConfigException {
		if (configuration instanceof ContextAwareConfig) {
			ContextAwareConfig config = (ContextAwareConfig) configuration;

			ContextAwareRepository repo = new ContextAwareRepository();

			repo.setIncludeInferred(config.isIncludeInferred());
			repo.setMaxQueryTime(config.getMaxQueryTime());
			repo.setQueryLanguage(config.getQueryLanguage());
			repo.setBaseURI(config.getBaseURI());
			repo.setReadContexts(config.getReadContexts());
			repo.setAddContexts(config.getAddContexts());
			repo.setRemoveContexts(config.getRemoveContexts());
			repo.setArchiveContexts(config.getArchiveContexts());
			repo.setInsertContext(config.getInsertContext());

			return repo;
		}

		throw new RepositoryConfigException("Invalid configuration class: " + configuration.getClass());
	}
}
