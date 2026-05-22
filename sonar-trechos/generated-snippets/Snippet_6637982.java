public class Snippet__6637982 {

    private static GraphRequest createGrantedPermissionsRequest(
                AccessToken accessToken,
                GraphRequest.Callback callback
        ) {
            Bundle parameters = new Bundle();
            return new GraphRequest(
                    accessToken,
                    ME_PERMISSIONS_GRAPH_PATH,
                    parameters,
                    HttpMethod.GET,
                    callback);
        }

}
