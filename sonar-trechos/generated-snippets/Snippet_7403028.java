public class Snippet__7403028 {

    @Override
        public void visitFullInfopointNode(FullInfopointNode i) {
            if (i.getState() != null && i.getState().bci == BytecodeFrame.AFTER_BCI) {
                i.getDebug().log("Ignoring InfopointNode for AFTER_BCI");
            } else {
                super.visitFullInfopointNode(i);
            }
        }

}
