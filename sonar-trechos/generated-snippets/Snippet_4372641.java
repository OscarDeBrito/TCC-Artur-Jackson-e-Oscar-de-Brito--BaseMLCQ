public class Snippet__4372641 {

    public void clientCreated(Client client) {
            for (ClientLifeCycleListener listener : listeners) {
                listener.clientCreated(client);
            }
        }

}
