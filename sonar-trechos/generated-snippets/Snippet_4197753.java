public class Snippet__4197753 {

    public static SetMultimap<InetAddressAndPort, Token> loadTokens()
        {
            SetMultimap<InetAddressAndPort, Token> tokenMap = HashMultimap.create();
            for (UntypedResultSet.Row row : executeInternal("SELECT peer, peer_port, tokens FROM system." + PEERS_V2))
            {
                InetAddress address = row.getInetAddress("peer");
                Integer port = row.getInt("peer_port");
                InetAddressAndPort peer = InetAddressAndPort.getByAddressOverrideDefaults(address, port);
                if (row.has("tokens"))
                    tokenMap.putAll(peer, deserializeTokens(row.getSet("tokens", UTF8Type.instance)));
            }

            return tokenMap;
        }

}
