public class Snippet__5235970 {

    public static Map<String, Object> getXmlRpcElementHashTable(Element element) {
        Map<String, Object> elementHash = new Hashtable<String, Object>();

        elementHash.put("id", element.getElementId());
        elementHash.put("name", element.getElementName());
        elementHash.put("dcElement", element.getDCElement() != null ? element
            .getDCElement() : "");
        elementHash.put("description",
            element.getDescription() != null ? element.getDescription()
                                             : "");

        return elementHash;
      }

}
