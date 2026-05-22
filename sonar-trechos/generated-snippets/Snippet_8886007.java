@org.apache.hadoop.classification.InterfaceAudience.Public @org.apache.hadoop.classification.InterfaceStability.Stable public static class cancel_delegation_token<I extends Iface> extends org.apache.thrift.ProcessFunction<I, cancel_delegation_token_args> {
      public cancel_delegation_token() {
        super("cancel_delegation_token");
      }

      public cancel_delegation_token_args getEmptyArgsInstance() {
        return new cancel_delegation_token_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public cancel_delegation_token_result getResult(I iface, cancel_delegation_token_args args) throws org.apache.thrift.TException {
        cancel_delegation_token_result result = new cancel_delegation_token_result();
        try {
          iface.cancel_delegation_token(args.token_str_form);
        } catch (MetaException o1) {
          result.o1 = o1;
        }
        return result;
      }
    }
