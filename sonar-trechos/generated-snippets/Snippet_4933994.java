public abstract class AttributeHandler extends ParseSax.HandlerForGeneratedRequestWithResult<String> {

   public static class PoolName extends AttributeHandler {
      public PoolName() {
         super("PoolName");
      }
   }

   private final String attributeName;
   private String attribute = null;

   private AttributeHandler(String attributeName) {
      this.attributeName = checkNotNull(attributeName, "attributeName");
   }

   @Override
   public String getResult() {
      return checkNotNull(attribute, "%s not present in the response", attributeName);
   }

   @Override
   public void startElement(String uri, String localName, String qName, Attributes attrs) {
      Map<String, String> attributes = cleanseAttributes(attrs);
      if (attribute == null && attributes.containsKey(attributeName)) {
         attribute = attributes.get(attributeName);
      }
   }
}
