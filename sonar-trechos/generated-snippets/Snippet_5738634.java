public static class setBlobMeta<I extends AsyncIface> extends org.apache.storm.thrift.AsyncProcessFunction<I, setBlobMeta_args, Void> {
      public setBlobMeta() {
        super("setBlobMeta");
      }

      public setBlobMeta_args getEmptyArgsInstance() {
        return new setBlobMeta_args();
      }

      public org.apache.storm.thrift.async.AsyncMethodCallback<Void> getResultHandler(final org.apache.storm.thrift.server.AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
        final org.apache.storm.thrift.AsyncProcessFunction fcall = this;
        return new org.apache.storm.thrift.async.AsyncMethodCallback<Void>() { 
          public void onComplete(Void o) {
            setBlobMeta_result result = new setBlobMeta_result();
            try {
              fcall.sendResponse(fb, result, org.apache.storm.thrift.protocol.TMessageType.REPLY,seqid);
            } catch (org.apache.storm.thrift.transport.TTransportException e) {
              _LOGGER.error("TTransportException writing to internal frame buffer", e);
              fb.close();
            } catch (java.lang.Exception e) {
              _LOGGER.error("Exception writing to internal frame buffer", e);
              onError(e);
            }
          }
          public void onError(java.lang.Exception e) {
            byte msgType = org.apache.storm.thrift.protocol.TMessageType.REPLY;
            org.apache.storm.thrift.TSerializable msg;
            setBlobMeta_result result = new setBlobMeta_result();
            if (e instanceof AuthorizationException) {
              result.aze = (AuthorizationException) e;
              result.set_aze_isSet(true);
              msg = result;
            } else if (e instanceof KeyNotFoundException) {
              result.knf = (KeyNotFoundException) e;
              result.set_knf_isSet(true);
              msg = result;
            } else if (e instanceof org.apache.storm.thrift.transport.TTransportException) {
              _LOGGER.error("TTransportException inside handler", e);
              fb.close();
              return;
            } else if (e instanceof org.apache.storm.thrift.TApplicationException) {
              _LOGGER.error("TApplicationException inside handler", e);
              msgType = org.apache.storm.thrift.protocol.TMessageType.EXCEPTION;
              msg = (org.apache.storm.thrift.TApplicationException)e;
            } else {
              _LOGGER.error("Exception inside handler", e);
              msgType = org.apache.storm.thrift.protocol.TMessageType.EXCEPTION;
              msg = new org.apache.storm.thrift.TApplicationException(org.apache.storm.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
            }
            try {
              fcall.sendResponse(fb,msg,msgType,seqid);
            } catch (java.lang.Exception ex) {
              _LOGGER.error("Exception writing to internal frame buffer", ex);
              fb.close();
            }
          }
        };
      }

      protected boolean isOneway() {
        return false;
      }

      public void start(I iface, setBlobMeta_args args, org.apache.storm.thrift.async.AsyncMethodCallback<Void> resultHandler) throws org.apache.storm.thrift.TException {
        iface.setBlobMeta(args.key, args.meta,resultHandler);
      }
    }
