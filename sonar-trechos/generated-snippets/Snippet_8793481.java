private static class getDetailedExperimentTree_resultTupleScheme extends TupleScheme<getDetailedExperimentTree_result> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, getDetailedExperimentTree_result struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetSuccess()) {
          optionals.set(0);
        }
        if (struct.isSetRse()) {
          optionals.set(1);
        }
        if (struct.isSetEnf()) {
          optionals.set(2);
        }
        oprot.writeBitSet(optionals, 3);
        if (struct.isSetSuccess()) {
          struct.success.write(oprot);
        }
        if (struct.isSetRse()) {
          struct.rse.write(oprot);
        }
        if (struct.isSetEnf()) {
          struct.enf.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, getDetailedExperimentTree_result struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(3);
        if (incoming.get(0)) {
          struct.success = new org.apache.airavata.model.experiment.ExperimentModel();
          struct.success.read(iprot);
          struct.setSuccessIsSet(true);
        }
        if (incoming.get(1)) {
          struct.rse = new org.apache.airavata.registry.api.exception.RegistryServiceException();
          struct.rse.read(iprot);
          struct.setRseIsSet(true);
        }
        if (incoming.get(2)) {
          struct.enf = new org.apache.airavata.model.error.ExperimentNotFoundException();
          struct.enf.read(iprot);
          struct.setEnfIsSet(true);
        }
      }
    }
