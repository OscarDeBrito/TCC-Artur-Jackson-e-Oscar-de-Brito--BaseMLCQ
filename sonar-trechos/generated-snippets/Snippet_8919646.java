@org.apache.hadoop.hive.common.classification.InterfaceAudience.Public @org.apache.hadoop.hive.common.classification.InterfaceStability.Stable public static class FetchResults<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, FetchResults_args, TFetchResultsResp> {
      public FetchResults() {
        super("FetchResults");
      }

      public FetchResults_args getEmptyArgsInstance() {
        return new FetchResults_args();
      }

      public AsyncMethodCallback<TFetchResultsResp> getResultHandler(final AsyncFrameBuffer fb, final int seqid) {
        final org.apache.thrift.AsyncProcessFunction fcall = this;
        return new AsyncMethodCallback<TFetchResultsResp>() { 
          public void onComplete(TFetchResultsResp o) {
            FetchResults_result result = new FetchResults_result();
            result.success = o;
            try {
              fcall.sendResponse(fb,result, org.apache.thrift.protocol.TMessageType.REPLY,seqid);
              return;
            } catch (Exception e) {
              LOGGER.error("Exception writing to internal frame buffer", e);
            }
            fb.close();
          }
          public void onError(Exception e) {
            byte msgType = org.apache.thrift.protocol.TMessageType.REPLY;
            org.apache.thrift.TBase msg;
            FetchResults_result result = new FetchResults_result();
            {
              msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
              msg = (org.apache.thrift.TBase)new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
            }
            try {
              fcall.sendResponse(fb,msg,msgType,seqid);
              return;
            } catch (Exception ex) {
              LOGGER.error("Exception writing to internal frame buffer", ex);
            }
            fb.close();
          }
        };
      }

      protected boolean isOneway() {
        return false;
      }

      public void start(I iface, FetchResults_args args, org.apache.thrift.async.AsyncMethodCallback<TFetchResultsResp> resultHandler) throws TException {
        iface.FetchResults(args.req,resultHandler);
      }
    }
