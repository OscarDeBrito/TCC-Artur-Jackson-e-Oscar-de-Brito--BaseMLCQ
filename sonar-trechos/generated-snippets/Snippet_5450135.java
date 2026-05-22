public class Snippet__5450135 {

    protected void handleDefaultEscapeXML() {

          PortletConfig portletConfig = (PortletConfig) pageContext.getRequest().getAttribute(Constants.PORTLET_CONFIG);
          Map<String, String[]> containerRuntimeOptions = portletConfig.getContainerRuntimeOptions();
          if (containerRuntimeOptions != null) {
             String[] result = containerRuntimeOptions.get(Constants.ESCAPE_XML_RUNTIME_OPTION);
             if (result != null) {
                if (result.length > 0) {
                   setEscapeXml(result[0]);
                }
             }
          }

    	}

}
