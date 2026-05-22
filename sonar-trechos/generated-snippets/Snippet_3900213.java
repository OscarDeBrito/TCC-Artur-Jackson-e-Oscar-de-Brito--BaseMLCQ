public static class exportTable<I extends Iface> extends org.apache.thrift.ProcessFunction<I, exportTable_args> {
      public exportTable() {
        super("exportTable");
      }

      public exportTable_args getEmptyArgsInstance() {
        return new exportTable_args();
      }

      protected boolean isOneway() {
        return false;
      }

      @Override
      protected boolean rethrowUnhandledExceptions() {
        return false;
      }

      public exportTable_result getResult(I iface, exportTable_args args) throws org.apache.thrift.TException {
        exportTable_result result = new exportTable_result();
        try {
          iface.exportTable(args.login, args.tableName, args.exportDir);
        } catch (AccumuloException ouch1) {
          result.ouch1 = ouch1;
        } catch (AccumuloSecurityException ouch2) {
          result.ouch2 = ouch2;
        } catch (TableNotFoundException ouch3) {
          result.ouch3 = ouch3;
        }
        return result;
      }
    }
