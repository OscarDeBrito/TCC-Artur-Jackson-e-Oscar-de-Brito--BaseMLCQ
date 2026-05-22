public class Snippet__5210111 {

    private Group parseGroup(final Element element) {
            final Group.Builder builder = new Group.Builder()
                    .identifier(element.getAttribute(IDENTIFIER_ATTR))
                    .name(element.getAttribute(NAME_ATTR));

            NodeList groupUsers = element.getElementsByTagName(GROUP_USER_ELEMENT);
            for (int i=0; i < groupUsers.getLength(); i++) {
                Element groupUserNode = (Element) groupUsers.item(i);
                builder.addUser(groupUserNode.getAttribute(IDENTIFIER_ATTR));
            }

            return builder.build();
        }

}
