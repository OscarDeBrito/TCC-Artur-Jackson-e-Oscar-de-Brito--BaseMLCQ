private static class startMultiScan_argsTupleScheme extends org.apache.thrift.scheme.TupleScheme<startMultiScan_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, startMultiScan_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet optionals = new java.util.BitSet();
        if (struct.isSetTinfo()) {
          optionals.set(0);
        }
        if (struct.isSetCredentials()) {
          optionals.set(1);
        }
        if (struct.isSetBatch()) {
          optionals.set(2);
        }
        if (struct.isSetColumns()) {
          optionals.set(3);
        }
        if (struct.isSetSsiList()) {
          optionals.set(4);
        }
        if (struct.isSetSsio()) {
          optionals.set(5);
        }
        if (struct.isSetAuthorizations()) {
          optionals.set(6);
        }
        if (struct.isSetWaitForWrites()) {
          optionals.set(7);
        }
        if (struct.isSetSamplerConfig()) {
          optionals.set(8);
        }
        if (struct.isSetBatchTimeOut()) {
          optionals.set(9);
        }
        if (struct.isSetClassLoaderContext()) {
          optionals.set(10);
        }
        if (struct.isSetExecutionHints()) {
          optionals.set(11);
        }
        oprot.writeBitSet(optionals, 12);
        if (struct.isSetTinfo()) {
          struct.tinfo.write(oprot);
        }
        if (struct.isSetCredentials()) {
          struct.credentials.write(oprot);
        }
        if (struct.isSetBatch()) {
          {
            oprot.writeI32(struct.batch.size());
            for (java.util.Map.Entry<org.apache.accumulo.core.dataImpl.thrift.TKeyExtent, java.util.List<org.apache.accumulo.core.dataImpl.thrift.TRange>> _iter206 : struct.batch.entrySet())
            {
              _iter206.getKey().write(oprot);
              {
                oprot.writeI32(_iter206.getValue().size());
                for (org.apache.accumulo.core.dataImpl.thrift.TRange _iter207 : _iter206.getValue())
                {
                  _iter207.write(oprot);
                }
              }
            }
          }
        }
        if (struct.isSetColumns()) {
          {
            oprot.writeI32(struct.columns.size());
            for (org.apache.accumulo.core.dataImpl.thrift.TColumn _iter208 : struct.columns)
            {
              _iter208.write(oprot);
            }
          }
        }
        if (struct.isSetSsiList()) {
          {
            oprot.writeI32(struct.ssiList.size());
            for (org.apache.accumulo.core.dataImpl.thrift.IterInfo _iter209 : struct.ssiList)
            {
              _iter209.write(oprot);
            }
          }
        }
        if (struct.isSetSsio()) {
          {
            oprot.writeI32(struct.ssio.size());
            for (java.util.Map.Entry<java.lang.String, java.util.Map<java.lang.String,java.lang.String>> _iter210 : struct.ssio.entrySet())
            {
              oprot.writeString(_iter210.getKey());
              {
                oprot.writeI32(_iter210.getValue().size());
                for (java.util.Map.Entry<java.lang.String, java.lang.String> _iter211 : _iter210.getValue().entrySet())
                {
                  oprot.writeString(_iter211.getKey());
                  oprot.writeString(_iter211.getValue());
                }
              }
            }
          }
        }
        if (struct.isSetAuthorizations()) {
          {
            oprot.writeI32(struct.authorizations.size());
            for (java.nio.ByteBuffer _iter212 : struct.authorizations)
            {
              oprot.writeBinary(_iter212);
            }
          }
        }
        if (struct.isSetWaitForWrites()) {
          oprot.writeBool(struct.waitForWrites);
        }
        if (struct.isSetSamplerConfig()) {
          struct.samplerConfig.write(oprot);
        }
        if (struct.isSetBatchTimeOut()) {
          oprot.writeI64(struct.batchTimeOut);
        }
        if (struct.isSetClassLoaderContext()) {
          oprot.writeString(struct.classLoaderContext);
        }
        if (struct.isSetExecutionHints()) {
          {
            oprot.writeI32(struct.executionHints.size());
            for (java.util.Map.Entry<java.lang.String, java.lang.String> _iter213 : struct.executionHints.entrySet())
            {
              oprot.writeString(_iter213.getKey());
              oprot.writeString(_iter213.getValue());
            }
          }
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, startMultiScan_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet incoming = iprot.readBitSet(12);
        if (incoming.get(0)) {
          struct.tinfo = new org.apache.accumulo.core.trace.thrift.TInfo();
          struct.tinfo.read(iprot);
          struct.setTinfoIsSet(true);
        }
        if (incoming.get(1)) {
          struct.credentials = new org.apache.accumulo.core.securityImpl.thrift.TCredentials();
          struct.credentials.read(iprot);
          struct.setCredentialsIsSet(true);
        }
        if (incoming.get(2)) {
          {
            org.apache.thrift.protocol.TMap _map214 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRUCT, org.apache.thrift.protocol.TType.LIST, iprot.readI32());
            struct.batch = new java.util.HashMap<org.apache.accumulo.core.dataImpl.thrift.TKeyExtent,java.util.List<org.apache.accumulo.core.dataImpl.thrift.TRange>>(2*_map214.size);
            @org.apache.thrift.annotation.Nullable org.apache.accumulo.core.dataImpl.thrift.TKeyExtent _key215;
            @org.apache.thrift.annotation.Nullable java.util.List<org.apache.accumulo.core.dataImpl.thrift.TRange> _val216;
            for (int _i217 = 0; _i217 < _map214.size; ++_i217)
            {
              _key215 = new org.apache.accumulo.core.dataImpl.thrift.TKeyExtent();
              _key215.read(iprot);
              {
                org.apache.thrift.protocol.TList _list218 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
                _val216 = new java.util.ArrayList<org.apache.accumulo.core.dataImpl.thrift.TRange>(_list218.size);
                @org.apache.thrift.annotation.Nullable org.apache.accumulo.core.dataImpl.thrift.TRange _elem219;
                for (int _i220 = 0; _i220 < _list218.size; ++_i220)
                {
                  _elem219 = new org.apache.accumulo.core.dataImpl.thrift.TRange();
                  _elem219.read(iprot);
                  _val216.add(_elem219);
                }
              }
              struct.batch.put(_key215, _val216);
            }
          }
          struct.setBatchIsSet(true);
        }
        if (incoming.get(3)) {
          {
            org.apache.thrift.protocol.TList _list221 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
            struct.columns = new java.util.ArrayList<org.apache.accumulo.core.dataImpl.thrift.TColumn>(_list221.size);
            @org.apache.thrift.annotation.Nullable org.apache.accumulo.core.dataImpl.thrift.TColumn _elem222;
            for (int _i223 = 0; _i223 < _list221.size; ++_i223)
            {
              _elem222 = new org.apache.accumulo.core.dataImpl.thrift.TColumn();
              _elem222.read(iprot);
              struct.columns.add(_elem222);
            }
          }
          struct.setColumnsIsSet(true);
        }
        if (incoming.get(4)) {
          {
            org.apache.thrift.protocol.TList _list224 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
            struct.ssiList = new java.util.ArrayList<org.apache.accumulo.core.dataImpl.thrift.IterInfo>(_list224.size);
            @org.apache.thrift.annotation.Nullable org.apache.accumulo.core.dataImpl.thrift.IterInfo _elem225;
            for (int _i226 = 0; _i226 < _list224.size; ++_i226)
            {
              _elem225 = new org.apache.accumulo.core.dataImpl.thrift.IterInfo();
              _elem225.read(iprot);
              struct.ssiList.add(_elem225);
            }
          }
          struct.setSsiListIsSet(true);
        }
        if (incoming.get(5)) {
          {
            org.apache.thrift.protocol.TMap _map227 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.MAP, iprot.readI32());
            struct.ssio = new java.util.HashMap<java.lang.String,java.util.Map<java.lang.String,java.lang.String>>(2*_map227.size);
            @org.apache.thrift.annotation.Nullable java.lang.String _key228;
            @org.apache.thrift.annotation.Nullable java.util.Map<java.lang.String,java.lang.String> _val229;
            for (int _i230 = 0; _i230 < _map227.size; ++_i230)
            {
              _key228 = iprot.readString();
              {
                org.apache.thrift.protocol.TMap _map231 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
                _val229 = new java.util.HashMap<java.lang.String,java.lang.String>(2*_map231.size);
                @org.apache.thrift.annotation.Nullable java.lang.String _key232;
                @org.apache.thrift.annotation.Nullable java.lang.String _val233;
                for (int _i234 = 0; _i234 < _map231.size; ++_i234)
                {
                  _key232 = iprot.readString();
                  _val233 = iprot.readString();
                  _val229.put(_key232, _val233);
                }
              }
              struct.ssio.put(_key228, _val229);
            }
          }
          struct.setSsioIsSet(true);
        }
        if (incoming.get(6)) {
          {
            org.apache.thrift.protocol.TList _list235 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
            struct.authorizations = new java.util.ArrayList<java.nio.ByteBuffer>(_list235.size);
            @org.apache.thrift.annotation.Nullable java.nio.ByteBuffer _elem236;
            for (int _i237 = 0; _i237 < _list235.size; ++_i237)
            {
              _elem236 = iprot.readBinary();
              struct.authorizations.add(_elem236);
            }
          }
          struct.setAuthorizationsIsSet(true);
        }
        if (incoming.get(7)) {
          struct.waitForWrites = iprot.readBool();
          struct.setWaitForWritesIsSet(true);
        }
        if (incoming.get(8)) {
          struct.samplerConfig = new TSamplerConfiguration();
          struct.samplerConfig.read(iprot);
          struct.setSamplerConfigIsSet(true);
        }
        if (incoming.get(9)) {
          struct.batchTimeOut = iprot.readI64();
          struct.setBatchTimeOutIsSet(true);
        }
        if (incoming.get(10)) {
          struct.classLoaderContext = iprot.readString();
          struct.setClassLoaderContextIsSet(true);
        }
        if (incoming.get(11)) {
          {
            org.apache.thrift.protocol.TMap _map238 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
            struct.executionHints = new java.util.HashMap<java.lang.String,java.lang.String>(2*_map238.size);
            @org.apache.thrift.annotation.Nullable java.lang.String _key239;
            @org.apache.thrift.annotation.Nullable java.lang.String _val240;
            for (int _i241 = 0; _i241 < _map238.size; ++_i241)
            {
              _key239 = iprot.readString();
              _val240 = iprot.readString();
              struct.executionHints.put(_key239, _val240);
            }
          }
          struct.setExecutionHintsIsSet(true);
        }
      }
    }
