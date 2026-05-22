public static class getAllUserResourceProfiles<I extends Iface> extends org.apache.thrift.ProcessFunction<I, getAllUserResourceProfiles_args> {
      public getAllUserResourceProfiles() {
        super("getAllUserResourceProfiles");
      }

      public getAllUserResourceProfiles_args getEmptyArgsInstance() {
        return new getAllUserResourceProfiles_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public getAllUserResourceProfiles_result getResult(I iface, getAllUserResourceProfiles_args args) throws org.apache.thrift.TException {
        getAllUserResourceProfiles_result result = new getAllUserResourceProfiles_result();
        try {
          result.success = iface.getAllUserResourceProfiles();
        } catch (org.apache.airavata.registry.api.exception.RegistryServiceException rse) {
          result.rse = rse;
        }
        return result;
      }
    }
