public static class FieldTypes extends AbstractSchemaRequest<SchemaResponse.FieldTypesResponse> {
    public FieldTypes() {
      this(null);
    }

    public FieldTypes(SolrParams q) {
      super(METHOD.GET, "/schema/fieldtypes");
    }

    @Override
    protected SchemaResponse.FieldTypesResponse createResponse(SolrClient client) {
      return new SchemaResponse.FieldTypesResponse();
    }
  }
