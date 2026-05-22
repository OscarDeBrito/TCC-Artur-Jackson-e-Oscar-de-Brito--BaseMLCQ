public class Snippet__8430551 {

    public RouteDefinition from(String uri) {
            getRouteCollection().setCamelContext(getContext());
            RouteDefinition answer = getRouteCollection().from(uri);
            configureRoute(answer);
            return answer;
        }

}
