private class DiscoveryListener implements GridLocalEventListener, HighPriorityListener {
        /** {@inheritDoc} */
        @Override public void onEvent(Event evt) {
            assert evt instanceof DiscoveryEvent;
            assert evt.type() == EVT_NODE_LEFT || evt.type() == EVT_NODE_FAILED;

            UUID nodeId = ((DiscoveryEvent)evt).eventNode().id();

            if (discoProtoVer == 2) {
                routinesInfo.onNodeFail(nodeId);

                for (StartFuture fut : startFuts.values())
                    fut.onNodeFail(nodeId);
            }

            clientInfos.remove(nodeId);

            // Unregister handlers created by left node.
            for (Map.Entry<UUID, RemoteRoutineInfo> e : rmtInfos.entrySet()) {
                UUID routineId = e.getKey();
                RemoteRoutineInfo info = e.getValue();

                if (nodeId.equals(info.nodeId)) {
                    if (info.autoUnsubscribe)
                        unregisterRemote(routineId);

                    if (info.hnd.isQuery())
                        info.hnd.onNodeLeft();
                }
            }

            for (Map.Entry<IgniteUuid, SyncMessageAckFuture> e : syncMsgFuts.entrySet()) {
                SyncMessageAckFuture fut = e.getValue();

                if (fut.nodeId().equals(nodeId)) {
                    SyncMessageAckFuture fut0 = syncMsgFuts.remove(e.getKey());

                    if (fut0 != null) {
                        ClusterTopologyCheckedException err = new ClusterTopologyCheckedException(
                            "Node left grid while sending message to: " + nodeId);

                        fut0.onDone(err);
                    }
                }
            }
        }

        /** {@inheritDoc} */
        @Override public int order() {
            return 1;
        }
    }
