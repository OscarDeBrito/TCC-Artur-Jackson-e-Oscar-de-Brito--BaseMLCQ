public class Snippet__7448715 {

    @Override
        public void close() {
            HSObject.invalidate(locals);
            if (parent == null) {
                if (topScope.get() != this) {
                    throw new IllegalStateException("Unexpected JNI scope: " + topScope.get());
                }
                topScope.set(null);
                objResult = PopLocalFrame(env, objResult);
            } else {
                HotSpotToSVMScope top = parent;
                while (top.parent != null) {
                    top = top.parent;
                }
                top.leaf = parent;
            }
            HotSpotToSVMEntryPoints.trace(1, "HS->SVM[ exit]: %s", id);
        }

}
