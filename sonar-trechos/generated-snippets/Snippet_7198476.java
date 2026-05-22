public class Snippet__7198476 {

    @Override
        public void setRegion(String regionId) {
            regionId = regionId.toLowerCase(Locale.ENGLISH);
            try {
                com.oracle.bmc.Region region = com.oracle.bmc.Region.fromRegionId(regionId);
                setRegion(region);
            } catch (IllegalArgumentException e) {
                LOG.info("Unknown regionId '{}', falling back to default endpoint format", regionId);
                String endpoint = com.oracle.bmc.Region.formatDefaultRegionEndpoint(SERVICE, regionId);
                setEndpoint(endpoint);
            }
        }

}
