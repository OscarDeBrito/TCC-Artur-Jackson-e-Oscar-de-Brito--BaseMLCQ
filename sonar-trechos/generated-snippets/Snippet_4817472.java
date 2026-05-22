public class Snippet__4817472 {

    public static String getRequiredAPIElements(int iRequestType)
      {
        String strElements = "";
    
        // cases higher up in the list are supersets of those that come below -
        // hence no "break" statements are required, because 'falling through' the
        // switch statement is the desired behaviour in this case
        switch (iRequestType) {
          case Resource.REQUEST_FULL_PREVIEW:
            strElements += "created-at,updated-at,internal-pack-items,external-pack-items,tags,comments,";
          case Resource.REQUEST_FULL_LISTING:
            strElements += "owner,";
          case Resource.REQUEST_SHORT_LISTING:
            strElements += "id,title,description,privileges";
        }
    
        return (strElements);
      }

}
