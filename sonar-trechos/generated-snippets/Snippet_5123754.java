public class Snippet__5123754 {

    @Override
      protected boolean noteNonscriptTag(String tagName, Map<String,String> attributes)
        throws ManifoldCFException
      {
        if (super.noteNonscriptTag(tagName,attributes))
          return true;
        if (tagName.equals("meta"))
        {
          handler.noteMetaTag(attributes);
        }
        return false;
      }

}
