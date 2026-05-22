private static class SpoutStatsTupleScheme extends org.apache.storm.thrift.scheme.TupleScheme<SpoutStats> {

    @Override
    public void write(org.apache.storm.thrift.protocol.TProtocol prot, SpoutStats struct) throws org.apache.storm.thrift.TException {
      org.apache.storm.thrift.protocol.TTupleProtocol oprot = (org.apache.storm.thrift.protocol.TTupleProtocol) prot;
      {
        oprot.writeI32(struct.acked.size());
        for (java.util.Map.Entry<java.lang.String, java.util.Map<java.lang.String,java.lang.Long>> _iter290 : struct.acked.entrySet())
        {
          oprot.writeString(_iter290.getKey());
          {
            oprot.writeI32(_iter290.getValue().size());
            for (java.util.Map.Entry<java.lang.String, java.lang.Long> _iter291 : _iter290.getValue().entrySet())
            {
              oprot.writeString(_iter291.getKey());
              oprot.writeI64(_iter291.getValue());
            }
          }
        }
      }
      {
        oprot.writeI32(struct.failed.size());
        for (java.util.Map.Entry<java.lang.String, java.util.Map<java.lang.String,java.lang.Long>> _iter292 : struct.failed.entrySet())
        {
          oprot.writeString(_iter292.getKey());
          {
            oprot.writeI32(_iter292.getValue().size());
            for (java.util.Map.Entry<java.lang.String, java.lang.Long> _iter293 : _iter292.getValue().entrySet())
            {
              oprot.writeString(_iter293.getKey());
              oprot.writeI64(_iter293.getValue());
            }
          }
        }
      }
      {
        oprot.writeI32(struct.complete_ms_avg.size());
        for (java.util.Map.Entry<java.lang.String, java.util.Map<java.lang.String,java.lang.Double>> _iter294 : struct.complete_ms_avg.entrySet())
        {
          oprot.writeString(_iter294.getKey());
          {
            oprot.writeI32(_iter294.getValue().size());
            for (java.util.Map.Entry<java.lang.String, java.lang.Double> _iter295 : _iter294.getValue().entrySet())
            {
              oprot.writeString(_iter295.getKey());
              oprot.writeDouble(_iter295.getValue());
            }
          }
        }
      }
    }

    @Override
    public void read(org.apache.storm.thrift.protocol.TProtocol prot, SpoutStats struct) throws org.apache.storm.thrift.TException {
      org.apache.storm.thrift.protocol.TTupleProtocol iprot = (org.apache.storm.thrift.protocol.TTupleProtocol) prot;
      {
        org.apache.storm.thrift.protocol.TMap _map296 = new org.apache.storm.thrift.protocol.TMap(org.apache.storm.thrift.protocol.TType.STRING, org.apache.storm.thrift.protocol.TType.MAP, iprot.readI32());
        struct.acked = new java.util.HashMap<java.lang.String,java.util.Map<java.lang.String,java.lang.Long>>(2*_map296.size);
        @org.apache.storm.thrift.annotation.Nullable java.lang.String _key297;
        @org.apache.storm.thrift.annotation.Nullable java.util.Map<java.lang.String,java.lang.Long> _val298;
        for (int _i299 = 0; _i299 < _map296.size; ++_i299)
        {
          _key297 = iprot.readString();
          {
            org.apache.storm.thrift.protocol.TMap _map300 = new org.apache.storm.thrift.protocol.TMap(org.apache.storm.thrift.protocol.TType.STRING, org.apache.storm.thrift.protocol.TType.I64, iprot.readI32());
            _val298 = new java.util.HashMap<java.lang.String,java.lang.Long>(2*_map300.size);
            @org.apache.storm.thrift.annotation.Nullable java.lang.String _key301;
            long _val302;
            for (int _i303 = 0; _i303 < _map300.size; ++_i303)
            {
              _key301 = iprot.readString();
              _val302 = iprot.readI64();
              _val298.put(_key301, _val302);
            }
          }
          struct.acked.put(_key297, _val298);
        }
      }
      struct.set_acked_isSet(true);
      {
        org.apache.storm.thrift.protocol.TMap _map304 = new org.apache.storm.thrift.protocol.TMap(org.apache.storm.thrift.protocol.TType.STRING, org.apache.storm.thrift.protocol.TType.MAP, iprot.readI32());
        struct.failed = new java.util.HashMap<java.lang.String,java.util.Map<java.lang.String,java.lang.Long>>(2*_map304.size);
        @org.apache.storm.thrift.annotation.Nullable java.lang.String _key305;
        @org.apache.storm.thrift.annotation.Nullable java.util.Map<java.lang.String,java.lang.Long> _val306;
        for (int _i307 = 0; _i307 < _map304.size; ++_i307)
        {
          _key305 = iprot.readString();
          {
            org.apache.storm.thrift.protocol.TMap _map308 = new org.apache.storm.thrift.protocol.TMap(org.apache.storm.thrift.protocol.TType.STRING, org.apache.storm.thrift.protocol.TType.I64, iprot.readI32());
            _val306 = new java.util.HashMap<java.lang.String,java.lang.Long>(2*_map308.size);
            @org.apache.storm.thrift.annotation.Nullable java.lang.String _key309;
            long _val310;
            for (int _i311 = 0; _i311 < _map308.size; ++_i311)
            {
              _key309 = iprot.readString();
              _val310 = iprot.readI64();
              _val306.put(_key309, _val310);
            }
          }
          struct.failed.put(_key305, _val306);
        }
      }
      struct.set_failed_isSet(true);
      {
        org.apache.storm.thrift.protocol.TMap _map312 = new org.apache.storm.thrift.protocol.TMap(org.apache.storm.thrift.protocol.TType.STRING, org.apache.storm.thrift.protocol.TType.MAP, iprot.readI32());
        struct.complete_ms_avg = new java.util.HashMap<java.lang.String,java.util.Map<java.lang.String,java.lang.Double>>(2*_map312.size);
        @org.apache.storm.thrift.annotation.Nullable java.lang.String _key313;
        @org.apache.storm.thrift.annotation.Nullable java.util.Map<java.lang.String,java.lang.Double> _val314;
        for (int _i315 = 0; _i315 < _map312.size; ++_i315)
        {
          _key313 = iprot.readString();
          {
            org.apache.storm.thrift.protocol.TMap _map316 = new org.apache.storm.thrift.protocol.TMap(org.apache.storm.thrift.protocol.TType.STRING, org.apache.storm.thrift.protocol.TType.DOUBLE, iprot.readI32());
            _val314 = new java.util.HashMap<java.lang.String,java.lang.Double>(2*_map316.size);
            @org.apache.storm.thrift.annotation.Nullable java.lang.String _key317;
            double _val318;
            for (int _i319 = 0; _i319 < _map316.size; ++_i319)
            {
              _key317 = iprot.readString();
              _val318 = iprot.readDouble();
              _val314.put(_key317, _val318);
            }
          }
          struct.complete_ms_avg.put(_key313, _val314);
        }
      }
      struct.set_complete_ms_avg_isSet(true);
    }
  }
