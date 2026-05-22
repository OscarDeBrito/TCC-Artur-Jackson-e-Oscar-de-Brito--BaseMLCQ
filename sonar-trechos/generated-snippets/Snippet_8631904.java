public class Snippet__8631904 {

    private BPlusTree.TreeRowClosure<H2Row, H2Row> filter(QueryContext qctx) {
            if (qctx == null) {
                assert !cctx.mvccEnabled();

                return null;
            }

            IndexingQueryFilter f = qctx.filter();
            IndexingQueryCacheFilter p = f == null ? null : f.forCache(getTable().cacheName());
            MvccSnapshot v = qctx.mvccSnapshot();

            assert !cctx.mvccEnabled() || v != null;

            if(p == null && v == null)
                return null;

            return new H2TreeFilterClosure(p, v, cctx, log);
        }

}
