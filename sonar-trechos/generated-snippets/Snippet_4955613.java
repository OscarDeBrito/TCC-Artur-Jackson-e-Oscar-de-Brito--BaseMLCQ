public class PayerHandler extends ParseSax.HandlerWithResult<Payer> {
   private StringBuilder currentText = new StringBuilder();
   private Payer constraint;

   public Payer getResult() {
      return constraint;
   }

   public void endElement(String uri, String name, String qName) {
      constraint = Payer.fromValue(currentOrNull(currentText));
   }

   public void characters(char[] ch, int start, int length) {
      currentText.append(ch, start, length);
   }
}
