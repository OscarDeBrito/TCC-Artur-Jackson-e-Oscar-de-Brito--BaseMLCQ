public class Snippet__5409414 {

    @GET
        @Path("list")
        public Collection<Subject> list() {
            return dao.findAll();
        }

}
