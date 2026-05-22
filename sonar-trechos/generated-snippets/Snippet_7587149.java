public class Snippet__7587149 {

    private Reference getReference(final Attributes attributes) {
        return new Reference(attributes.getValue(DatabaseXmlUtils.LOCAL),
            attributes.getValue(DatabaseXmlUtils.FOREIGN));
      }

}
