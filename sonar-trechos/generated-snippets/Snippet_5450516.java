public class DispatcherReqRespTests5S_SPEC2_19_ForwardServletActionRequest implements Portlet, ResourceServingPortlet {
   
   private PortletConfig portletConfig = null;

   @Override
   public void init(PortletConfig config) throws PortletException {
      this.portletConfig = config;
   }

   @Override
   public void destroy() {
   }

   @Override
   public void processAction(ActionRequest portletReq, ActionResponse portletResp)
         throws PortletException, IOException {

      portletResp.setRenderParameters(portletReq.getParameterMap());
      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

      // Now do the actual dispatch
      String target = SERVLET_PREFIX + "DispatcherReqRespTests5S_SPEC2_19_ForwardServletActionRequest_servlet" + SERVLET_SUFFIX + "?" + QUERY_STRING;
      PortletRequestDispatcher rd = portletConfig.getPortletContext()
            .getRequestDispatcher(target);
      rd.forward(portletReq, portletResp);
   }

   @Override
   public void serveResource(ResourceRequest portletReq, ResourceResponse portletResp)
         throws PortletException, IOException {

      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

   }

   @Override
   public void render(RenderRequest portletReq, RenderResponse portletResp)
         throws PortletException, IOException {

      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

      PrintWriter writer = portletResp.getWriter();

      PortletSession ps = portletReq.getPortletSession();
      String msg = (String) ps.getAttribute(RESULT_ATTR_PREFIX + "DispatcherReqRespTests5S_SPEC2_19_ForwardServletActionRequest", APPLICATION_SCOPE);
      if (msg != null) {
         writer.write("<p>" + msg + "</p><br/>\n");
         ps.removeAttribute(RESULT_ATTR_PREFIX + "DispatcherReqRespTests5S_SPEC2_19_ForwardServletActionRequest", APPLICATION_SCOPE);
      }

      /* TestCase: V2DispatcherReqRespTests5S_SPEC2_19_ForwardServletActionRequest_getInputStream */
      /* Details: "In a target servlet of a forward in the Action phase,      */
      /* the method HttpServletRequest.getInputStream must provide the same   */
      /* functionality as ActionRequest.getPortletInputStream"                */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton(V2DISPATCHERREQRESPTESTS5S_SPEC2_19_FORWARDSERVLETACTIONREQUEST_GETINPUTSTREAM, aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherReqRespTests5S_SPEC2_19_ForwardServletActionRequest_setCharacterEncoding */
      /* Details: "In a target servlet of a forward in the Action phase,      */
      /* the method HttpServletRequest.setCharacterEncoding must provide      */
      /* the same functionality as ActionRequest.setCharacterEncoding"        */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton(V2DISPATCHERREQRESPTESTS5S_SPEC2_19_FORWARDSERVLETACTIONREQUEST_SETCHARACTERENCODING, aurl);
         tb.writeTo(writer);
      }

   }

}
