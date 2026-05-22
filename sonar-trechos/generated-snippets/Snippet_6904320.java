public class Snippet__6904320 {

    @Override
            public CapturedType cloneWithMetadata(TypeMetadata md) {
                return new CapturedType(tsym, bound, bound, lower, wildcard, md) {
                    @Override
                    public Type baseType() { return CapturedType.this.baseType(); }
                };
            }

}
