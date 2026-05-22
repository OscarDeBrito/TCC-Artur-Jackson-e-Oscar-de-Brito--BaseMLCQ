public class Snippet__4440109 {

    private IdentificationCardScan createScan (final String customerIdentifier, final String cardNumber) throws Exception {
        final IdentificationCardScan scan = ScanGenerator.createRandomScan(null);

        this.postIdentificationCardScan(customerIdentifier, cardNumber, scan);

        return scan;
      }

}
