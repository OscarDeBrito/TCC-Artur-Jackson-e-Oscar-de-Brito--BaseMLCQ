public static class getGatewayResourceProfile<I extends Iface> extends org.apache.thrift.ProcessFunction<I, getGatewayResourceProfile_args> {
      public getGatewayResourceProfile() {
        super("getGatewayResourceProfile");
      }

      public getGatewayResourceProfile_args getEmptyArgsInstance() {
        return new getGatewayResourceProfile_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public getGatewayResourceProfile_result getResult(I iface, getGatewayResourceProfile_args args) throws org.apache.thrift.TException {
        getGatewayResourceProfile_result result = new getGatewayResourceProfile_result();
        try {
          result.success = iface.getGatewayResourceProfile(args.authzToken, args.gatewayID);
        } catch (org.apache.airavata.model.error.InvalidRequestException ire) {
          result.ire = ire;
        } catch (org.apache.airavata.model.error.AiravataClientException ace) {
          result.ace = ace;
        } catch (org.apache.airavata.model.error.AiravataSystemException ase) {
          result.ase = ase;
        } catch (org.apache.airavata.model.error.AuthorizationException ae) {
          result.ae = ae;
        }
        return result;
      }
    }
