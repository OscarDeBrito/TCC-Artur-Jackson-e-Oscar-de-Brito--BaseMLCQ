public static class update<I extends Iface> extends org.apache.thrift.ProcessFunction<I, update_args> {
      public update() {
        super("update");
      }

      public update_args getEmptyArgsInstance() {
        return new update_args();
      }

      protected boolean isOneway() {
        return false;
      }

      @Override
      protected boolean rethrowUnhandledExceptions() {
        return false;
      }

      public update_result getResult(I iface, update_args args) throws org.apache.thrift.TException {
        update_result result = new update_result();
        try {
          iface.update(args.tinfo, args.credentials, args.keyExtent, args.mutation, args.durability);
        } catch (org.apache.accumulo.core.clientImpl.thrift.ThriftSecurityException sec) {
          result.sec = sec;
        } catch (NotServingTabletException nste) {
          result.nste = nste;
        } catch (ConstraintViolationException cve) {
          result.cve = cve;
        }
        return result;
      }
    }
