public class Snippet__5124033 {

    protected static void handleIOException(IOException e, String context)
        throws ManifoldCFException, ServiceInterruption
      {
        if (e instanceof java.net.SocketException)
          throw new ManifoldCFException("Socket timeout error "+context+": "+e.getMessage(),e);
        else if (e instanceof ConnectTimeoutException)
          throw new ManifoldCFException("Socket connect timeout error "+context+": "+e.getMessage(),e);
        else if (e instanceof InterruptedIOException)
          throw new ManifoldCFException("Interrupted: "+e.getMessage(),e,ManifoldCFException.INTERRUPTED);
        else
          throw new ManifoldCFException("IO error "+context+": "+e.getMessage(),e);
      }

}
