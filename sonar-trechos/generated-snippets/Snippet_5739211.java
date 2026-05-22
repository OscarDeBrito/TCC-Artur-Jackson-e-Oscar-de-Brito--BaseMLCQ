public interface IServer {
    void channelActive(Channel c);

    void received(Object message, String remote, Channel channel) throws InterruptedException;
}
