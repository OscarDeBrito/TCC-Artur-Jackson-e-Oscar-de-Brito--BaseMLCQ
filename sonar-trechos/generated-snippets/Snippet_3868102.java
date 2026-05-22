public class Snippet__3868102 {

    public String getDefaultPrompt() {
        Objects.requireNonNull(accumuloClient);
        ClientInfo info = ClientInfo.from(accumuloClient.properties());
        return accumuloClient.whoami() + "@" + info.getInstanceName()
            + (getTableName().isEmpty() ? "" : " ") + getTableName() + "> ";
      }

}
