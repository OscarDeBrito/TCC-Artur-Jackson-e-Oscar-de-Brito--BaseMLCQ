public class Snippet__4043104 {

    public int getMaxFramesForWindow() {
            int frameSize = getFrameSize();
            return getInt(MAX_FRAMES_FOR_WINDOW, (int) (((long) 4 * MB) / frameSize));
        }

}
