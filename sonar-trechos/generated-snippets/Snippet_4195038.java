public class Snippet__4195038 {

    public boolean matchesEndpoints(Set<InetAddressAndPort> endpoints, Set<InetAddressAndPort> transEndpoints)
        {
            // Use strict equality here, as worst thing that can happen is we generate one more stream
            return this.endpoints.equals(endpoints) && this.transEndpoints.equals(transEndpoints);
        }

}
