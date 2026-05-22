public static class grantSystemPermission<I extends Iface> extends org.apache.thrift.ProcessFunction<I, grantSystemPermission_args> {
      public grantSystemPermission() {
        super("grantSystemPermission");
      }

      public grantSystemPermission_args getEmptyArgsInstance() {
        return new grantSystemPermission_args();
      }

      protected boolean isOneway() {
        return false;
      }

      @Override
      protected boolean rethrowUnhandledExceptions() {
        return false;
      }

      public grantSystemPermission_result getResult(I iface, grantSystemPermission_args args) throws org.apache.thrift.TException {
        grantSystemPermission_result result = new grantSystemPermission_result();
        try {
          iface.grantSystemPermission(args.login, args.user, args.perm);
        } catch (AccumuloException ouch1) {
          result.ouch1 = ouch1;
        } catch (AccumuloSecurityException ouch2) {
          result.ouch2 = ouch2;
        }
        return result;
      }
    }
