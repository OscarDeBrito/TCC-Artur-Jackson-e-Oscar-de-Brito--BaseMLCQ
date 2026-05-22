private static class getUnicoreJobSubmission_argsTupleScheme extends TupleScheme<getUnicoreJobSubmission_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, getUnicoreJobSubmission_args struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        struct.authzToken.write(oprot);
        oprot.writeString(struct.jobSubmissionId);
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, getUnicoreJobSubmission_args struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        struct.authzToken = new org.apache.airavata.model.security.AuthzToken();
        struct.authzToken.read(iprot);
        struct.setAuthzTokenIsSet(true);
        struct.jobSubmissionId = iprot.readString();
        struct.setJobSubmissionIdIsSet(true);
      }
    }
