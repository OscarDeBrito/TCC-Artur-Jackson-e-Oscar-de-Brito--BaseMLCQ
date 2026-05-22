public class Snippet__5025413 {

    synchronized
      public
      void removeAllAppenders() {
        if(aai != null) {
          Vector appenders = new Vector();
          for (Enumeration iter = aai.getAllAppenders(); iter != null && iter.hasMoreElements();) {
              appenders.add(iter.nextElement());
          }
          aai.removeAllAppenders();
          for(Enumeration iter = appenders.elements(); iter.hasMoreElements();) {
              fireRemoveAppenderEvent((Appender) iter.nextElement());
          }
          aai = null;
        }
      }

}
