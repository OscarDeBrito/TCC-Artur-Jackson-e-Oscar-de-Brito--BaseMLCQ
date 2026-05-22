@FunctionalInterface
public interface ClassPathRestartStrategy {

	/**
	 * Return true if a full restart is required.
	 * @param file the changed file
	 * @return {@code true} if a full restart is required
	 */
	boolean isRestartRequired(ChangedFile file);

}
