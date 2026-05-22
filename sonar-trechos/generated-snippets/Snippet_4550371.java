public class Snippet__4550371 {

    @Override
    	public void validateSelfUserAccountMapping(Long accountId) {

    		if (!this.appuserSavingsMapperReadService.isSavingsMappedToUser(accountId,
    				this.context.getAuthenticatedUserIfPresent().getId())) {
    			throw new SavingsAccountNotFoundException(accountId);

    		}
    	}

}
