public class Snippet__4574673 {

    public void addChildLMs(List newLMs) {
            if (newLMs == null || newLMs.size() == 0) {
                return;
            }
            for (LayoutManager newLM : (Iterable<LayoutManager>) newLMs) {
                addChildLM(newLM);
            }
        }

}
