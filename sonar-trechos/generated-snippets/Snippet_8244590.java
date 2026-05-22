public class Snippet__8244590 {

    public static int getAttributeAsInteger(Element element, 
                String attributeName) throws Exception {
            String attrValue = getAttribute(element, attributeName);
            try {
                return Integer.parseInt(attrValue);
            } catch (NumberFormatException e) {
                throw new Exception("Cannot parse the value \"" + attrValue + 
                        "\" as an integer in the attribute \"" + attributeName + 
                        "\" on the element \"" + element.getTagName() + 
                        "\" at " + getLocation(element));
            }
        }

}
