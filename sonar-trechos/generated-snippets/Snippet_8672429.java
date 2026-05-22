private final class UserAcceptedListener implements CustomEventListener<UserAcceptedMessage> {
        /** {@inheritDoc} */
        @Override public void onCustomEvent(AffinityTopologyVersion topVer, ClusterNode snd, UserAcceptedMessage msg) {
            if (!isEnabled || ctx.isStopping())
                return;

            if (log.isDebugEnabled())
                log.debug(msg.toString());

            synchronized (mux) {
                UserOperationFinishFuture f = opFinishFuts.get(msg.operationId());

                if (f != null) {
                    if (msg.error() != null)
                        f.onDone(null, msg.error());
                    else
                        f.onDone();
                }
            }
        }
    }
