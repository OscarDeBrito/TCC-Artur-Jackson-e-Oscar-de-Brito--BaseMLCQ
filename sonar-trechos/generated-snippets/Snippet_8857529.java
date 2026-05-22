public class Snippet__8857529 {

    @Override
          public void write(org.apache.thrift.protocol.TProtocol prot, registerApplicationModule_args struct) throws org.apache.thrift.TException {
            TTupleProtocol oprot = (TTupleProtocol) prot;
            struct.authzToken.write(oprot);
            oprot.writeString(struct.gatewayId);
            struct.applicationModule.write(oprot);
          }

}
