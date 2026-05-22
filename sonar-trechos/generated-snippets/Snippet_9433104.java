public class Snippet__9433104 {

    private void writeObject(ObjectOutputStream s)
          throws java.io.IOException
        {
          s.defaultWriteObject();

          AWTEventMulticaster.save(s, itemListenerK, itemListener);
          s.writeObject(null);
        }

}
