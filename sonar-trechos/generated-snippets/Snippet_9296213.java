public class Snippet__9296213 {

    @Override
        public ArrayData copy() {
            final UndefinedArrayFilter copy = new UndefinedArrayFilter(underlying.copy());
            copy.getUndefined().copy(undefined);
            return copy;
        }

}
