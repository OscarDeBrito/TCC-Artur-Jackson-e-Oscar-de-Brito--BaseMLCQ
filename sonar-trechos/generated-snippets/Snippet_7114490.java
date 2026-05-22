public class Snippet__7114490 {

    public void setOutputProperty(String name, String value)
              throws IllegalArgumentException
      {

        synchronized (m_reentryGuard)
        {

          // Get the output format that was set by the user, otherwise get the 
          // output format from the stylesheet.
          if (null == m_outputFormat)
          {
            m_outputFormat =
              (OutputProperties) getStylesheet().getOutputComposed().clone();
          }

          if (!OutputProperties.isLegalPropertyKey(name))
            throw new IllegalArgumentException(XSLMessages.createMessage(XSLTErrorResources.ER_OUTPUT_PROPERTY_NOT_RECOGNIZED, new Object[]{name})); //"output property not recognized: "
                                               //+ name);

          m_outputFormat.setProperty(name, value);
        }
      }

}
