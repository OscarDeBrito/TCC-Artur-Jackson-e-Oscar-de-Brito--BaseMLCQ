public class Snippet__4364662 {

    @Override
        public void writeTo(JsonWebKeys t, Class<?> type, Type genericType, Annotation[] annotations,
                            MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
                            OutputStream entityStream) throws IOException, WebApplicationException {
            JwkUtils.jwkSetToJson(t, entityStream);

        }

}
