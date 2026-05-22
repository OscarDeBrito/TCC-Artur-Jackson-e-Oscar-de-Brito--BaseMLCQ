public class Snippet__7400506 {

    public int rewindUpTo(VirtualFrame frame, int length) {
            if (props.isForward()) {
                final int offset = Math.min(getIndex(frame), length);
                setIndex(frame, getIndex(frame) - offset);
                return offset;
            } else {
                assert length == 0;
                return 0;
            }
        }

}
