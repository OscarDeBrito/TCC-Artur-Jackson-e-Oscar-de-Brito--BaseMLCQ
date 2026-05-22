@FunctionalInterface
public interface SessionFactoryLocator<F> {

	/**
	 * Return a {@link SessionFactory} for the key.
	 * @param key the key.
	 * @return the session factory.
	 */
	SessionFactory<F> getSessionFactory(Object key);

}
