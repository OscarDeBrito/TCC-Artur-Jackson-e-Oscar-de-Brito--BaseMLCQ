public class Snippet__7168092 {

    public void setProfileAttributes(
            final VssJsonCollectionWrapper<List<ProfileAttributeBase<Object>>> attributesCollection, 
            final String id) { 

            final UUID locationId = UUID.fromString("1392b6ac-d511-492e-af5b-2263e5545a5d"); //$NON-NLS-1$
            final ApiResourceVersion apiVersion = new ApiResourceVersion("3.1-preview.2"); //$NON-NLS-1$

            final Map<String, Object> routeValues = new HashMap<String, Object>();
            routeValues.put("id", id); //$NON-NLS-1$

            final VssRestRequest httpRequest = super.createRequest(HttpMethod.PATCH,
                                                                   locationId,
                                                                   routeValues,
                                                                   apiVersion,
                                                                   attributesCollection,
                                                                   VssMediaTypes.APPLICATION_JSON_TYPE,
                                                                   VssMediaTypes.APPLICATION_JSON_TYPE);

            super.sendRequest(httpRequest);
        }

}
