public class Snippet__8231238 {

    public WebContinuation getContinuation(int level) {
            if (level <= 0) {
                return this;
            } else if (parentContinuation == null) {
                return this;
            } else {
                return parentContinuation.getContinuation(level - 1);
            }
        }

}
