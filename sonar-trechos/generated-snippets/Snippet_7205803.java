public class Snippet__7205803 {

    private boolean canRetryRequestIfRefreshableAuthTokenUsed(com.oracle.bmc.model.BmcException e) {
            if (e.getStatusCode() == 401
                    && this.authenticationDetailsProvider
                            instanceof com.oracle.bmc.auth.RefreshableOnNotAuthenticatedProvider) {
                ((com.oracle.bmc.auth.RefreshableOnNotAuthenticatedProvider)
                                this.authenticationDetailsProvider)
                        .refresh();
                return true;
            }
            return false;
        }

}
