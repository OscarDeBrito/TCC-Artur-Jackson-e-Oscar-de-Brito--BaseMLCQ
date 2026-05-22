public static class getChildDataProducts<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, getChildDataProducts_args, List<org.apache.airavata.model.data.replica.DataProductModel>> {
      public getChildDataProducts() {
        super("getChildDataProducts");
      }

      public getChildDataProducts_args getEmptyArgsInstance() {
        return new getChildDataProducts_args();
      }

      public AsyncMethodCallback<List<org.apache.airavata.model.data.replica.DataProductModel>> getResultHandler(final AsyncFrameBuffer fb, final int seqid) {
        final org.apache.thrift.AsyncProcessFunction fcall = this;
        return new AsyncMethodCallback<List<org.apache.airavata.model.data.replica.DataProductModel>>() { 
          public void onComplete(List<org.apache.airavata.model.data.replica.DataProductModel> o) {
            getChildDataProducts_result result = new getChildDataProducts_result();
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
            getChildDataProducts_result result = new getChildDataProducts_result();
            if (e instanceof org.apache.airavata.model.error.InvalidRequestException) {
                        result.ire = (org.apache.airavata.model.error.InvalidRequestException) e;
                        result.setIreIsSet(true);
                        msg = result;
            }
            else             if (e instanceof org.apache.airavata.model.error.AiravataClientException) {
                        result.ace = (org.apache.airavata.model.error.AiravataClientException) e;
                        result.setAceIsSet(true);
                        msg = result;
            }
            else             if (e instanceof org.apache.airavata.model.error.AiravataSystemException) {
                        result.ase = (org.apache.airavata.model.error.AiravataSystemException) e;
                        result.setAseIsSet(true);
                        msg = result;
            }
            else             if (e instanceof org.apache.airavata.model.error.AuthorizationException) {
                        result.ae = (org.apache.airavata.model.error.AuthorizationException) e;
                        result.setAeIsSet(true);
                        msg = result;
            }
             else 
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

      public void start(I iface, getChildDataProducts_args args, org.apache.thrift.async.AsyncMethodCallback<List<org.apache.airavata.model.data.replica.DataProductModel>> resultHandler) throws TException {
        iface.getChildDataProducts(args.authzToken, args.productUri,resultHandler);
      }
    }
