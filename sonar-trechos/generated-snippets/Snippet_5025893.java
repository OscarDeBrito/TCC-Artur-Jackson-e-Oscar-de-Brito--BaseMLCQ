public class Snippet__5025893 {

    private
      void writeObject(ObjectOutputStream oos) throws java.io.IOException {
        // Aside from returning the current thread name the wgetThreadName
        // method sets the threadName variable.
        this.getThreadName();

        // This sets the renders the message in case it wasn't up to now.
        this.getRenderedMessage();

        // This call has a side effect of setting this.ndc and
        // setting ndcLookupRequired to false if not already false.
        this.getNDC();

        // This call has a side effect of setting this.mdcCopy and
        // setting mdcLookupRequired to false if not already false.
        this.getMDCCopy();

        // This sets the throwable sting representation of the event throwable.
        this.getThrowableStrRep();

        oos.defaultWriteObject();

        // serialize this event's level
        writeLevel(oos);
      }

}
