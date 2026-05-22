public class Snippet__6062576 {

    public ContentHandle getContent(Node node) throws IOException {
            ContentHandle result = peekContent(node);
            if (result != null) {
                return result;
            }
        
            if (outstore != null) {
                installNpmModule(node);
                return outstore.getContent(node);
            } else {
                // Until now we used peekContent() which doesn't throw
                // so we re-try with any store to get the error we want
                return stores[0].getContent(node);
            }
        }

}
