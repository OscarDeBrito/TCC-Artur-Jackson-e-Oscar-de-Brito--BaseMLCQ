public class Snippet__3931329 {

    public static ServersShuttingDown getServersShuttingDown() {
        ServersShuttingDown servers = new ServersShuttingDown();
        // Add new servers to the list
        for (String server : Monitor.getMmi().serversShuttingDown) {
          servers.addServerShuttingDown(new ServerShuttingDownInformation(server));
        }
        return servers;
      }

}
