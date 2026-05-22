private class ClientLocalNodeWatcher extends PreviousNodeWatcher {
        final CheckJoinErrorWatcher joinErrorWatcher;

        /**
         * @param rtState Runtime state.
         */
        ClientLocalNodeWatcher(ZkRuntimeState rtState, CheckJoinErrorWatcher joinErrorWatcher) {
            super(rtState);

            assert locNode.isClient() : locNode;

            this.joinErrorWatcher = joinErrorWatcher;
        }

        /** {@inheritDoc} */
        @Override void onPreviousNodeFail() {
            // Check that there are no errors in join data.
            joinErrorWatcher.checkJoinError();

            if (rtState.errForClose != null || rtState.joined)
                return;

            synchronized (stateMux) {
                if (connState != ConnectionState.STARTED)
                    return;
            }

            if (log.isInfoEnabled())
                log.info("Watched local node failed [locId=" + locNode.id() + ']');

            localNodeFail("Local node was forced to stop.", true);
        }
    }
