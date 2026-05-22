public class Snippet__9375078 {

    private Snippet__9375078() {
                completionCF = new MinimalFuture<>();
                completionCF.whenComplete(
                        (r,t) -> subscribedCF.thenAccept( s -> complete(s, t)));
            }

}
