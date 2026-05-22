public interface SessionInformationExpiredStrategy {

	void onExpiredSessionDetected(SessionInformationExpiredEvent event)
			throws IOException, ServletException;
}
