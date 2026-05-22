public class Snippet__5110869 {

    @Override
      public void viewConfiguration(IThreadContext threadContext, IHTTPOutput out, Locale locale, ConfigParams parameters)
        throws ManifoldCFException, IOException
      {
        Map<String,Object> velocityContext = new HashMap<String,Object>();
        fillInDomainControllerTab(velocityContext,out,parameters);
        fillInCacheTab(velocityContext,out,parameters);
        Messages.outputResourceWithVelocity(out,locale,"viewADConfiguration.html",velocityContext);
      }

}
