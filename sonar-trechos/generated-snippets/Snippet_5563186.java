public static class list_sentry_privileges_for_provider<I extends Iface> extends org.apache.thrift.ProcessFunction<I, list_sentry_privileges_for_provider_args> {
      public list_sentry_privileges_for_provider() {
        super("list_sentry_privileges_for_provider");
      }

      public list_sentry_privileges_for_provider_args getEmptyArgsInstance() {
        return new list_sentry_privileges_for_provider_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public list_sentry_privileges_for_provider_result getResult(I iface, list_sentry_privileges_for_provider_args args) throws org.apache.thrift.TException {
        list_sentry_privileges_for_provider_result result = new list_sentry_privileges_for_provider_result();
        result.success = iface.list_sentry_privileges_for_provider(args.request);
        return result;
      }
    }
