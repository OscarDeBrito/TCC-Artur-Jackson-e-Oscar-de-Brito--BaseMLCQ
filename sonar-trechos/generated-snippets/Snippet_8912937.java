public static class MapJoinOneStringKeyInnerBigOnlyNativeVectorOptimizedBench extends MapJoinOneStringKeyBenchBase {

    @Setup
    public void setup() throws Exception {
      doSetup(VectorMapJoinVariation.INNER_BIG_ONLY, MapJoinTestImplementation.NATIVE_VECTOR_OPTIMIZED);
    }
  }
