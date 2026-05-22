public static class APIRequestCreateMessengerProfile extends APIRequest<Page> {

    Page lastResponse = null;
    @Override
    public Page getLastResponse() {
      return lastResponse;
    }
    public static final String[] PARAMS = {
      "get_started",
      "persistent_menu",
      "target_audience",
      "whitelisted_domains",
      "greeting",
      "account_linking_url",
      "payment_settings",
      "home_url",
    };

    public static final String[] FIELDS = {
    };

    @Override
    public Page parseResponse(String response, String header) throws APIException {
      return Page.parseResponse(response, getContext(), this, header).head();
    }

    @Override
    public Page execute() throws APIException {
      return execute(new HashMap<String, Object>());
    }

    @Override
    public Page execute(Map<String, Object> extraParams) throws APIException {
      ResponseWrapper rw = executeInternal(extraParams);
      lastResponse = parseResponse(rw.getBody(), rw.getHeader());
      return lastResponse;
    }

    public ListenableFuture<Page> executeAsync() throws APIException {
      return executeAsync(new HashMap<String, Object>());
    };

    public ListenableFuture<Page> executeAsync(Map<String, Object> extraParams) throws APIException {
      return Futures.transform(
        executeAsyncInternal(extraParams),
        new Function<ResponseWrapper, Page>() {
           public Page apply(ResponseWrapper result) {
             try {
               return APIRequestCreateMessengerProfile.this.parseResponse(result.getBody(), result.getHeader());
             } catch (Exception e) {
               throw new RuntimeException(e);
             }
           }
         }
      );
    };

    public APIRequestCreateMessengerProfile(String nodeId, APIContext context) {
      super(context, nodeId, "/messenger_profile", "POST", Arrays.asList(PARAMS));
    }

    @Override
    public APIRequestCreateMessengerProfile setParam(String param, Object value) {
      setParamInternal(param, value);
      return this;
    }

    @Override
    public APIRequestCreateMessengerProfile setParams(Map<String, Object> params) {
      setParamsInternal(params);
      return this;
    }


    public APIRequestCreateMessengerProfile setGetStarted (Object getStarted) {
      this.setParam("get_started", getStarted);
      return this;
    }
    public APIRequestCreateMessengerProfile setGetStarted (String getStarted) {
      this.setParam("get_started", getStarted);
      return this;
    }

    public APIRequestCreateMessengerProfile setPersistentMenu (List<Object> persistentMenu) {
      this.setParam("persistent_menu", persistentMenu);
      return this;
    }
    public APIRequestCreateMessengerProfile setPersistentMenu (String persistentMenu) {
      this.setParam("persistent_menu", persistentMenu);
      return this;
    }

    public APIRequestCreateMessengerProfile setTargetAudience (Object targetAudience) {
      this.setParam("target_audience", targetAudience);
      return this;
    }
    public APIRequestCreateMessengerProfile setTargetAudience (String targetAudience) {
      this.setParam("target_audience", targetAudience);
      return this;
    }

    public APIRequestCreateMessengerProfile setWhitelistedDomains (List<String> whitelistedDomains) {
      this.setParam("whitelisted_domains", whitelistedDomains);
      return this;
    }
    public APIRequestCreateMessengerProfile setWhitelistedDomains (String whitelistedDomains) {
      this.setParam("whitelisted_domains", whitelistedDomains);
      return this;
    }

    public APIRequestCreateMessengerProfile setGreeting (List<Object> greeting) {
      this.setParam("greeting", greeting);
      return this;
    }
    public APIRequestCreateMessengerProfile setGreeting (String greeting) {
      this.setParam("greeting", greeting);
      return this;
    }

    public APIRequestCreateMessengerProfile setAccountLinkingUrl (String accountLinkingUrl) {
      this.setParam("account_linking_url", accountLinkingUrl);
      return this;
    }

    public APIRequestCreateMessengerProfile setPaymentSettings (Object paymentSettings) {
      this.setParam("payment_settings", paymentSettings);
      return this;
    }
    public APIRequestCreateMessengerProfile setPaymentSettings (String paymentSettings) {
      this.setParam("payment_settings", paymentSettings);
      return this;
    }

    public APIRequestCreateMessengerProfile setHomeUrl (Object homeUrl) {
      this.setParam("home_url", homeUrl);
      return this;
    }
    public APIRequestCreateMessengerProfile setHomeUrl (String homeUrl) {
      this.setParam("home_url", homeUrl);
      return this;
    }

    public APIRequestCreateMessengerProfile requestAllFields () {
      return this.requestAllFields(true);
    }

    public APIRequestCreateMessengerProfile requestAllFields (boolean value) {
      for (String field : FIELDS) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestCreateMessengerProfile requestFields (List<String> fields) {
      return this.requestFields(fields, true);
    }

    @Override
    public APIRequestCreateMessengerProfile requestFields (List<String> fields, boolean value) {
      for (String field : fields) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestCreateMessengerProfile requestField (String field) {
      this.requestField(field, true);
      return this;
    }

    @Override
    public APIRequestCreateMessengerProfile requestField (String field, boolean value) {
      this.requestFieldInternal(field, value);
      return this;
    }

  }
