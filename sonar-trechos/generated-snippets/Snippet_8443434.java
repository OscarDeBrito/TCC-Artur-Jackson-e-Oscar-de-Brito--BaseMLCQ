public class Snippet__8443434 {

    private Region messageToRegion(Message message) {
            Region region = message.getBody(Region.class);
            if (region == null) {
                Map headers = message.getHeaders();
                RegionBuilder builder = Builders.region();

                if (headers.containsKey(KeystoneConstants.DESCRIPTION)) {
                    builder.description(message.getHeader(KeystoneConstants.DESCRIPTION, String.class));
                }

                region = builder.build();
            }

            return region;
        }

}
