public class ListFaultDomainsExample {
    public static void main(String[] args) throws Exception {

        // TODO: Fill in this value
        String compartmentId = null;

        String configurationFilePath = "~/.oci/config";
        String profile = "DEFAULT";

        AuthenticationDetailsProvider provider =
                new ConfigFileAuthenticationDetailsProvider(configurationFilePath, profile);

        Identity identityClient = new IdentityClient(provider);

        List<AvailabilityDomain> availabilityDomains =
                identityClient
                        .listAvailabilityDomains(
                                ListAvailabilityDomainsRequest.builder()
                                        .compartmentId(compartmentId)
                                        .build())
                        .getItems();

        for (AvailabilityDomain ad : availabilityDomains) {

            ListFaultDomainsResponse listFaultDomainsResponse =
                    identityClient.listFaultDomains(
                            ListFaultDomainsRequest.builder()
                                    .compartmentId(ad.getCompartmentId())
                                    .availabilityDomain(ad.getName())
                                    .build());
            for (FaultDomain fd : listFaultDomainsResponse.getItems()) {
                System.out.println(fd.getName());
            }
        }
    }
}
