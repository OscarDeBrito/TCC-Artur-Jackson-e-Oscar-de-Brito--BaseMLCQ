public class Snippet__8801664 {

    protected XmlElement toXML() {
            XmlElement portElement = XMLUtil.BUILDER.newFragment(GraphSchema.NS, GraphSchema.PORT_TAG);

            XmlElement idElement = portElement.addElement(GraphSchema.NS, GraphSchema.PORT_ID_TAG);
            idElement.addChild(getID());

            if (this.name != null) {
                // TODO control ports might have name?
                XmlElement nameElement = portElement.addElement(GraphSchema.NS, GraphSchema.PORT_NAME_TAG);
                nameElement.addChild(this.name);
            }

            XmlElement nodeElement = portElement.addElement(GraphSchema.NS, GraphSchema.PORT_NODE_TAG);
            nodeElement.addChild(this.node.getID());

            return portElement;
        }

}
