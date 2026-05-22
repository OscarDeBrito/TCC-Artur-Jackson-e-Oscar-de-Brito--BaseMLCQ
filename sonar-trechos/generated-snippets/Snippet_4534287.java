public class Snippet__4534287 {

    @GET
        @Path("downloadtemplate")
        @Produces("application/vnd.ms-excel")
        public Response getSavingsTemplate(@QueryParam("officeId")final Long officeId,
                @QueryParam("staffId")final Long staffId,@QueryParam("dateFormat") final String dateFormat) {
            return bulkImportWorkbookPopulatorService.getTemplate(GlobalEntityType.SAVINGS_ACCOUNT.toString(),officeId, staffId,dateFormat);
        }

}
