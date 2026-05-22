public static class JCasGenThrower implements IError {

    private Level logLevels[] = { Level.INFO, Level.WARNING, Level.SEVERE };

    private String m_message = null;

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.uima.jcas.jcasgen_gen.IError#newError(int, java.lang.String)
     */
    public void newError(int severity, String message, Exception ex) {
      Logger log = UIMAFramework.getLogger();
      log.log(logLevels[severity], "JCasGen: " + message); //$NON-NLS-1$
      System.out.println(Messages.getString("MultiPageEditor.JCasGenErr") //$NON-NLS-1$
              + message);
      if (null != ex)
        ex.printStackTrace();
      if (IError.WARN < severity) {
        m_message = message;
        throw new Jg.ErrorExit();
      }
    }

    public String getMessage() {
      return m_message;
    }
  }
