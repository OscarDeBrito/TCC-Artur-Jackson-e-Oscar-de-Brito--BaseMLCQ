public class Snippet__4849361 {

    public Address buildAddress(ASTaddress node, DecodeMonitor monitor) throws ParseException {
            ChildNodeIterator it = new ChildNodeIterator(node);
            Node n = it.next();
            if (n instanceof ASTaddr_spec) {
                return buildAddrSpec((ASTaddr_spec) n);
            } else if (n instanceof ASTangle_addr) {
                return buildAngleAddr((ASTangle_addr) n);
            } else if (n instanceof ASTphrase) {
                String name = buildString((ASTphrase) n, false);
                Node n2 = it.next();
                if (n2 instanceof ASTgroup_body) {
                    return new Group(name, buildGroupBody((ASTgroup_body) n2, monitor));
                } else if (n2 instanceof ASTangle_addr) {
                    try {
                        name = DecoderUtil.decodeEncodedWords(name, monitor);
                    } catch (IllegalArgumentException e) {
                        throw new ParseException(e);
                    }
                    Mailbox mb = buildAngleAddr((ASTangle_addr) n2);
                    return new Mailbox(name, mb.getRoute(), mb.getLocalPart(),
                            mb.getDomain());
                } else {
                    throw new ParseException("Address \"group_body\" or \"angle_addr\" is expected but got \"" + n + "\"");
                }
            } else {
                throw new ParseException("Address \"phrase\" is expected but got \"" + n + "\"");
            }
        }

}
