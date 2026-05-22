public class Snippet__8644476 {

    public Snippet__8644476(
            long evtId,
            long topVer,
            List<ZkJoinedNodeEvtData> joinedNodes,
            int dataForJoinedPartCnt)
        {
            super(evtId, ZK_EVT_NODE_JOIN, topVer);

            this.joinedNodes = joinedNodes;
            this.dataForJoinedPartCnt = dataForJoinedPartCnt;
        }

}
