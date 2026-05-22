public class Snippet__6459836 {

    private void buildNode79()
            throws Exception {
            UaVariableNode node = new DataTypeDescriptionNode(this.context, NodeId.parse("ns=0;i=8567"), new QualifiedName(0, "ContentFilter"), new LocalizedText("en", "ContentFilter"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), new DataValue(Variant.NULL_VALUE), NodeId.parse("ns=0;i=12"), -1, new UInteger[]{}, UByte.valueOf(1), UByte.valueOf(1), 0.0D, false);
            node.addReference(new Reference(NodeId.parse("ns=0;i=8567"), NodeId.parse("ns=0;i=39"), ExpandedNodeId.parse("svr=0;i=587"), NodeClass.Object, false));
            node.addReference(new Reference(NodeId.parse("ns=0;i=8567"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=8252"), NodeClass.Variable, false));
            node.addReference(new Reference(NodeId.parse("ns=0;i=8567"), NodeId.parse("ns=0;i=40"), ExpandedNodeId.parse("svr=0;i=69"), NodeClass.VariableType, true));
            node.addReference(new Reference(NodeId.parse("ns=0;i=8567"), NodeId.parse("ns=0;i=47"), ExpandedNodeId.parse("svr=0;i=8252"), NodeClass.Variable, false));
            String valueXml;
            OpcUaXmlStreamDecoder decoder = new OpcUaXmlStreamDecoder(new StringReader(new String("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns2:String xmlns=\"http://opcfoundation.org/BinarySchema/\" xmlns:ns2=\"http://opcfoundation.org/UA/2008/02/Types.xsd\" xmlns:ns3=\"http://opcfoundation.org/UA/2011/03/UANodeSet.xsd\">//xs:element[@name='ContentFilter']</ns2:String>")));
            Object valueObject = decoder.readVariantValue();
            DataValue value = new DataValue(new Variant(valueObject));
            node.setValue(value);
            this.nodeManager.addNode(node);
        }

}
