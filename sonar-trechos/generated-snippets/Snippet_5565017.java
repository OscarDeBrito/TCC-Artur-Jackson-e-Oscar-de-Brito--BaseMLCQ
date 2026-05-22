public static class CommandImpl implements Command {
    @Override
    public void run(String[] args) throws Exception {
      SentryConfigTool sentryTool = new SentryConfigTool();

      try {
        // parse arguments
        sentryTool.parseArgs(args);

        // load configuration
        sentryTool.setupConfig();

        // validate configuration
        if (sentryTool.isValidate()) {
          sentryTool.validatePolicy();
        }

        if (!StringUtils.isEmpty(sentryTool.getImportPolicyFilePath())) {
          sentryTool.importPolicy();
        }

        if (!StringUtils.isEmpty(sentryTool.getExportPolicyFilePath())) {
          sentryTool.exportPolicy();
        }

        // list permissions for give user
        if (sentryTool.isListPrivs()) {
          sentryTool.listPrivs();
        }

        // verify given query
        if (sentryTool.getQuery() != null) {
          if (sentryTool.getJdbcURL() != null) {
            sentryTool.verifyRemoteQuery(sentryTool.getQuery());
          } else {
            sentryTool.verifyLocalQuery(sentryTool.getQuery());
          }
        }
      } catch (Exception e) {
        System.out.println("Sentry tool reported Errors: " + e.getMessage());
        e.printStackTrace(System.out);
        System.exit(1);
      }
    }
  }
