public class Snippet__7595459 {

    public boolean hasAnyScope(String... scopes) {
    		boolean result = OAuth2ExpressionUtils.hasAnyScope(authentication, scopes);
    		if (!result) {
    			missingScopes.addAll(Arrays.asList(scopes));
    		}
    		return result;
    	}

}
