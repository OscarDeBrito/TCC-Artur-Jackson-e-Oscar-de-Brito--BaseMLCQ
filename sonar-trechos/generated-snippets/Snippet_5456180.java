public class Snippet__5456180 {

    private void checkPostedFormData() 
        {
            if (getMethod().equals("POST"))
            {
                String contentType = getContentType();
                if (contentType == null || contentType.equals("application/x-www-form-urlencoded"))
                {
                    throw new IllegalStateException("User request HTTP POST data is of type "
                                                    + "application/x-www-form-urlencoded. "
                                                    + "This data has been already processed "
                                                    + "by the portlet-container and is available "
                                                    + "as request parameters.");
                }
            }
        }

}
