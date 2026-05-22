public class Snippet__8845236 {

    public void validate() throws org.apache.thrift.TException {
          // check for required fields
          if (authzToken == null) {
            throw new org.apache.thrift.protocol.TProtocolException("Required field 'authzToken' was not present! Struct: " + toString());
          }
          if (gatewayId == null) {
            throw new org.apache.thrift.protocol.TProtocolException("Required field 'gatewayId' was not present! Struct: " + toString());
          }
          // check for sub-struct validity
          if (authzToken != null) {
            authzToken.validate();
          }
        }

}
