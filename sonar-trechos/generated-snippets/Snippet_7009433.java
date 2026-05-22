public class Snippet__7009433 {

    @Provides
      @Parameter("fields")
      static Optional<String> provideFields(HttpServletRequest req) {
        return extractOptionalParameter(req, ListObjectsAction.FIELDS_PARAM);
      }

}
