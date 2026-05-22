public class Snippet__8928769 {

    public void initParseCtx(ParseContext pctx) {
        opToPartPruner = pctx.getOpToPartPruner();
        opToPartList = pctx.getOpToPartList();
        opToSamplePruner = pctx.getOpToSamplePruner();
        topOps = pctx.getTopOps();
        loadTableWork = pctx.getLoadTableWork();
        loadFileWork = pctx.getLoadFileWork();
        ctx = pctx.getContext();
        destTableId = pctx.getDestTableId();
        idToTableNameMap = pctx.getIdToTableNameMap();
        uCtx = pctx.getUCtx();
        listMapJoinOpsNoReducer = pctx.getListMapJoinOpsNoReducer();
        prunedPartitions = pctx.getPrunedPartitions();
        tabNameToTabObject = pctx.getTabNameToTabObject();
        fetchTask = pctx.getFetchTask();
        setLineageInfo(pctx.getLineageInfo());
      }

}
