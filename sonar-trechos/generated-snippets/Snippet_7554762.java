public class Snippet__7554762 {

    @Override
    	protected String getGatewayClassName(Element element) {
    		return ((StringUtils.hasText(element.getAttribute("marshaller"))) ?
    				MarshallingWebServiceOutboundGateway.class : SimpleWebServiceOutboundGateway.class).getName();
    	}

}
