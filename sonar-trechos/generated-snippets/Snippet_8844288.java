public class Snippet__8844288 {

    private Runnable getIAMUserProfileUpdater(AuthzToken authzToken, UserProfile userProfile) throws UserProfileServiceException {
            IamAdminServices.Client iamAdminServicesClient = getIamAdminServicesClient();
            return () -> {
                try {
                    iamAdminServicesClient.updateUserProfile(authzToken, userProfile);
                } catch (TException e) {
                    throw new RuntimeException("Failed to update user profile in IAM service", e);
                }
            };
        }

}
