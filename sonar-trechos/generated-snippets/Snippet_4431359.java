public class Snippet__4431359 {

    @Override
        protected Collection<IPZoneEntity> loadFromService(ISecurityDataEnrichServiceClient client) {
            return client.listIPZones();
        }

}
