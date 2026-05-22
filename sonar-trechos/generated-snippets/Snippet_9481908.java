public class Snippet__9481908 {

    public String getValueOrNull(String name) throws InvalidXmlException {
          if (!subtrees.containsKey(name)) {
            return null;
          }
          LinkedList <Stanza> l = subtrees.get(name);
          if (l.size() != 1) {
            throw new InvalidXmlException("More than one value found for " + name);
          }
          return l.get(0).getValue();
        }

}
