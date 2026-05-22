public static class APIRequestGet extends APIRequest<PageInsightsAsyncExportRun> {

    PageInsightsAsyncExportRun lastResponse = null;
    @Override
    public PageInsightsAsyncExportRun getLastResponse() {
      return lastResponse;
    }
    public static final String[] PARAMS = {
    };

    public static final String[] FIELDS = {
      "data_level",
      "filters",
      "format",
      "gen_report_date",
      "id",
      "report_end_date",
      "report_start_date",
      "sorters",
      "status",
    };

    @Override
    public PageInsightsAsyncExportRun parseResponse(String response, String header) throws APIException {
      return PageInsightsAsyncExportRun.parseResponse(response, getContext(), this, header).head();
    }

    @Override
    public PageInsightsAsyncExportRun execute() throws APIException {
      return execute(new HashMap<String, Object>());
    }

    @Override
    public PageInsightsAsyncExportRun execute(Map<String, Object> extraParams) throws APIException {
      ResponseWrapper rw = executeInternal(extraParams);
      lastResponse = parseResponse(rw.getBody(), rw.getHeader());
      return lastResponse;
    }

    public ListenableFuture<PageInsightsAsyncExportRun> executeAsync() throws APIException {
      return executeAsync(new HashMap<String, Object>());
    };

    public ListenableFuture<PageInsightsAsyncExportRun> executeAsync(Map<String, Object> extraParams) throws APIException {
      return Futures.transform(
        executeAsyncInternal(extraParams),
        new Function<ResponseWrapper, PageInsightsAsyncExportRun>() {
           public PageInsightsAsyncExportRun apply(ResponseWrapper result) {
             try {
               return APIRequestGet.this.parseResponse(result.getBody(), result.getHeader());
             } catch (Exception e) {
               throw new RuntimeException(e);
             }
           }
         }
      );
    };

    public APIRequestGet(String nodeId, APIContext context) {
      super(context, nodeId, "/", "GET", Arrays.asList(PARAMS));
    }

    @Override
    public APIRequestGet setParam(String param, Object value) {
      setParamInternal(param, value);
      return this;
    }

    @Override
    public APIRequestGet setParams(Map<String, Object> params) {
      setParamsInternal(params);
      return this;
    }


    public APIRequestGet requestAllFields () {
      return this.requestAllFields(true);
    }

    public APIRequestGet requestAllFields (boolean value) {
      for (String field : FIELDS) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestGet requestFields (List<String> fields) {
      return this.requestFields(fields, true);
    }

    @Override
    public APIRequestGet requestFields (List<String> fields, boolean value) {
      for (String field : fields) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestGet requestField (String field) {
      this.requestField(field, true);
      return this;
    }

    @Override
    public APIRequestGet requestField (String field, boolean value) {
      this.requestFieldInternal(field, value);
      return this;
    }

    public APIRequestGet requestDataLevelField () {
      return this.requestDataLevelField(true);
    }
    public APIRequestGet requestDataLevelField (boolean value) {
      this.requestField("data_level", value);
      return this;
    }
    public APIRequestGet requestFiltersField () {
      return this.requestFiltersField(true);
    }
    public APIRequestGet requestFiltersField (boolean value) {
      this.requestField("filters", value);
      return this;
    }
    public APIRequestGet requestFormatField () {
      return this.requestFormatField(true);
    }
    public APIRequestGet requestFormatField (boolean value) {
      this.requestField("format", value);
      return this;
    }
    public APIRequestGet requestGenReportDateField () {
      return this.requestGenReportDateField(true);
    }
    public APIRequestGet requestGenReportDateField (boolean value) {
      this.requestField("gen_report_date", value);
      return this;
    }
    public APIRequestGet requestIdField () {
      return this.requestIdField(true);
    }
    public APIRequestGet requestIdField (boolean value) {
      this.requestField("id", value);
      return this;
    }
    public APIRequestGet requestReportEndDateField () {
      return this.requestReportEndDateField(true);
    }
    public APIRequestGet requestReportEndDateField (boolean value) {
      this.requestField("report_end_date", value);
      return this;
    }
    public APIRequestGet requestReportStartDateField () {
      return this.requestReportStartDateField(true);
    }
    public APIRequestGet requestReportStartDateField (boolean value) {
      this.requestField("report_start_date", value);
      return this;
    }
    public APIRequestGet requestSortersField () {
      return this.requestSortersField(true);
    }
    public APIRequestGet requestSortersField (boolean value) {
      this.requestField("sorters", value);
      return this;
    }
    public APIRequestGet requestStatusField () {
      return this.requestStatusField(true);
    }
    public APIRequestGet requestStatusField (boolean value) {
      this.requestField("status", value);
      return this;
    }
  }
