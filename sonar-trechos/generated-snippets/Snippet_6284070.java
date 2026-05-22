public class Snippet__6284070 {

    @Override
        public boolean contains(String documentName) {
            requireNonNull(documentName, "documentName is required");
            return documents.containsKey(documentName);
        }

}
