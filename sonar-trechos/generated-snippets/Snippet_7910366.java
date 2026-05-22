public class Snippet__7910366 {

    public static TProtocol newProtocolInstance(ThriftProtocol protocol, TTransport transport) {
        return getProtocolFactory(protocol).getProtocol(transport);
      }

}
