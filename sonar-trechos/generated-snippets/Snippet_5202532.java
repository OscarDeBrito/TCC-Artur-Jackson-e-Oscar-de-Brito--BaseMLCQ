public class Snippet__5202532 {

    public Snippet__5202532(final String requestString) {
                final List<String> reqComponents = Arrays.asList(requestString.split(":"));
                final int numComponents = reqComponents.size();
                if (numComponents < EXPECTED_REQUEST_COMPONENTS) {
                    throw new IllegalArgumentException(String.format("Cannot perform a FlowStatusQuery request for '%s'.  Expected at least %d components but got %d.",
                            requestString, EXPECTED_REQUEST_COMPONENTS, numComponents));
                }
                this.queryType = reqComponents.get(0).toLowerCase();
                if (numComponents == 2) {
                    this.options = reqComponents.get(1);
                } else {
                    this.identifier = reqComponents.get(1);
                    this.options = reqComponents.get(2);
                }
                // normalize options
                this.options = this.options.toLowerCase();
            }

}
