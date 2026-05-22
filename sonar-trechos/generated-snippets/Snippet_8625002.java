public class Snippet__8625002 {

    @Override public <K1, V1> GridCacheProxyImpl<K1, V1> keepBinary() {
            if (opCtx != null && opCtx.isKeepBinary())
                return (GridCacheProxyImpl<K1, V1>)this;

            return new GridCacheProxyImpl<>((GridCacheContext<K1, V1>)ctx,
                (GridCacheAdapter<K1, V1>)delegate,
                opCtx != null ? opCtx.keepBinary() :
                    new CacheOperationContext(false, null, true, null, false, null, false, DFLT_ALLOW_ATOMIC_OPS_IN_TX));
        }

}
