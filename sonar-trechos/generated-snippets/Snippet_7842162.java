public class Snippet__7842162 {

    protected void createClientServerScenarion_SingleConnection(ArrayList commonAttributes,
          int localMaxMemoryServer1, int localMaxMemoryServer2, int localMaxMemoryServer3) {
        createCacheInClientServer();
        Integer port1 = (Integer) server1.invoke(() -> PRClientServerTestBase
            .createCacheServer(commonAttributes, new Integer(localMaxMemoryServer1)));
        server2.invoke(() -> PRClientServerTestBase.createCacheServer(commonAttributes,
            new Integer(localMaxMemoryServer2)));
        serverPort1 = port1;
        client.invoke(() -> PRClientServerTestBase.createCacheClient_SingleConnection(
            NetworkUtils.getServerHostName(server1.getHost()), port1));
      }

}
