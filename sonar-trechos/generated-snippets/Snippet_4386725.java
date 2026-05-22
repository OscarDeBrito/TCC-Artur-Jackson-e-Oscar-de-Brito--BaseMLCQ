public class Snippet__4386725 {

    private void validate()
        {
            setPageComplete( getApplyOnDns() != null || spw.isValid() );
            setErrorMessage( searchButton.getSelection() ? spw.getErrorMessage() : null );
        }

}
