public static class APIRequestCreateAuthorizedAdAccount extends APIRequest<InstagramUser> {

    InstagramUser lastResponse = null;
    @Override
    public InstagramUser getLastResponse() {
      return lastResponse;
    }
    public static final String[] PARAMS = {
      "account_id",
      "business",
    };

    public static final String[] FIELDS = {
    };

    @Override
    public InstagramUser parseResponse(String response, String header) throws APIException {
      return InstagramUser.parseResponse(response, getContext(), this, header).head();
    }

    @Override
    public InstagramUser execute() throws APIException {
      return execute(new HashMap<String, Object>());
    }

    @Override
    public InstagramUser execute(Map<String, Object> extraParams) throws APIException {
      ResponseWrapper rw = executeInternal(extraParams);
      lastResponse = parseResponse(rw.getBody(), rw.getHeader());
      return lastResponse;
    }

    public ListenableFuture<InstagramUser> executeAsync() throws APIException {
      return executeAsync(new HashMap<String, Object>());
    };

    public ListenableFuture<InstagramUser> executeAsync(Map<String, Object> extraParams) throws APIException {
      return Futures.transform(
        executeAsyncInternal(extraParams),
        new Function<ResponseWrapper, InstagramUser>() {
           public InstagramUser apply(ResponseWrapper result) {
             try {
               return APIRequestCreateAuthorizedAdAccount.this.parseResponse(result.getBody(), result.getHeader());
             } catch (Exception e) {
               throw new RuntimeException(e);
             }
           }
         }
      );
    };

    public APIRequestCreateAuthorizedAdAccount(String nodeId, APIContext context) {
      super(context, nodeId, "/authorized_adaccounts", "POST", Arrays.asList(PARAMS));
    }

    @Override
    public APIRequestCreateAuthorizedAdAccount setParam(String param, Object value) {
      setParamInternal(param, value);
      return this;
    }

    @Override
    public APIRequestCreateAuthorizedAdAccount setParams(Map<String, Object> params) {
      setParamsInternal(params);
      return this;
    }


    public APIRequestCreateAuthorizedAdAccount setAccountId (String accountId) {
      this.setParam("account_id", accountId);
      return this;
    }

    public APIRequestCreateAuthorizedAdAccount setBusiness (String business) {
      this.setParam("business", business);
      return this;
    }

    public APIRequestCreateAuthorizedAdAccount requestAllFields () {
      return this.requestAllFields(true);
    }

    public APIRequestCreateAuthorizedAdAccount requestAllFields (boolean value) {
      for (String field : FIELDS) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestCreateAuthorizedAdAccount requestFields (List<String> fields) {
      return this.requestFields(fields, true);
    }

    @Override
    public APIRequestCreateAuthorizedAdAccount requestFields (List<String> fields, boolean value) {
      for (String field : fields) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestCreateAuthorizedAdAccount requestField (String field) {
      this.requestField(field, true);
      return this;
    }

    @Override
    public APIRequestCreateAuthorizedAdAccount requestField (String field, boolean value) {
      this.requestFieldInternal(field, value);
      return this;
    }

  }
