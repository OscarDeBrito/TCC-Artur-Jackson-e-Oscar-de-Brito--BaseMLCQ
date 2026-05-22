public class Snippet__6507301 {

    public CordovaCLIResult version(final IProgressMonitor monitor) throws CoreException{
    		final CordovaCLIStreamListener streamListener = new CordovaCLIStreamListener();
    		IProcess process = startShell(streamListener, monitor, getLaunchConfiguration("cordova -version"));
    		String cordovaCommand = "cordova -version\n";
    		sendCordovaCommand(process, cordovaCommand, monitor);
    		CordovaCLIResult result = new CordovaCLIResult(streamListener.getMessage());
    		return result;		
    	}

}
