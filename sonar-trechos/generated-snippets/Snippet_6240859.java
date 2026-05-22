public class Snippet__6240859 {

    private boolean labelNextToPort(final LPort dummyPort, final boolean placeNextToPortIfPossible) {
            return placeNextToPortIfPossible
                    && dummyPort.getIncomingEdges().isEmpty()
                    && dummyPort.getOutgoingEdges().isEmpty();
        }

}
