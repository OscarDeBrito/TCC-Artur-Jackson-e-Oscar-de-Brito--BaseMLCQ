public class Snippet__7141052 {

    private static IClassFile resolveClassFile(String uriString) {
            if (uriString == null || uriString.isEmpty()) {
                return null;
            }
            try {
                URI uri = new URI(uriString);
                if (uri != null && JDT_SCHEME.equals(uri.getScheme()) && "contents".equals(uri.getAuthority())) {
                    String handleId = uri.getQuery();
                    IJavaElement element = JavaCore.create(handleId);
                    IClassFile cf = (IClassFile) element.getAncestor(IJavaElement.CLASS_FILE);
                    return cf;
                }
            } catch (URISyntaxException e) {
                // ignore
            }
            return null;
        }

}
