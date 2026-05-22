public class Snippet__5449838 {

    @GET
        @ApiOperation(
                value = "Downloads Pulsar Function file data",
                hidden = true
        )
        @Path("/download")
        public StreamingOutput downloadFunction(final @QueryParam("path") String path) {
            return functions.downloadFunction(path);
        }

}
