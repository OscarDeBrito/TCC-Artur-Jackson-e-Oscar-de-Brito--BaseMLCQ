@Component
@Service(Object.class)
@Property(name = "javax.ws.rs", boolValue = true)
@Path("/")
public class StanbolRootResource extends BaseStanbolResource {

    @GET
    @Produces(TEXT_HTML)
    public Response get() {
        return Response.ok(new Viewable("index", this), TEXT_HTML).build();
    }
    
}
