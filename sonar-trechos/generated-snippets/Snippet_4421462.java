@Path("server")
public class ServerResource {
    private @Inject Config config;

    @GET
    @Path("/version")
    @Produces( {"application/json"})
    public ServerConfig.ServerVersion version() {
        return ServerConfig.getServerVersion();
    }

    @GET
    @Path("/config")
    @Produces( {"application/json"})
    public String config() {
        return config.root().render(ConfigRenderOptions.concise());
    }
}
