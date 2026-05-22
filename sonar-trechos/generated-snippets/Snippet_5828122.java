@Component
public class MigrationTo122 implements Migration {
    private CloseableHttpClient httpClient;
    private Session session;
    private String esAddress;

    @Override
    public Version getFromVersion() {
        return null;
    }

    @Override
    public Version getToVersion() {
        return new Version("1.2.2");
    }

    @Override
    public String getDescription() {
        return "Delete old index template";
    }

    @Override
    public void execute(Session session, CloseableHttpClient httpClient, String esAddress) throws IOException {
        this.httpClient = httpClient;
        this.session = session;
        this.esAddress = esAddress;
        deleteOldIndexTemplate();

    }

    private void deleteOldIndexTemplate() throws IOException {
        String oldMonthlyIndexTemplate = "context_monthlyindex";
        try {
            ConsoleUtils.printMessage(session,"Deleting old monthly index template " + oldMonthlyIndexTemplate);
            HttpUtils.executeDeleteRequest(httpClient, esAddress + "/_template/" + oldMonthlyIndexTemplate, null);
        } catch (HttpRequestException e) {
            if (e.getCode() == 404) {
                ConsoleUtils.printMessage(session,"Old monthly index template not found, skipping deletion");
            } else {
                throw e;
            }
        }

    }
}
