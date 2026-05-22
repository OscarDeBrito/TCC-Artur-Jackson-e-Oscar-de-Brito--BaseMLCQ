public class Snippet__4798652 {

    public String getPassword() {
    		final UsernamePassword userPass = getUserPass();
    		final String pw = userPass.getPasswordAsString();
    		userPass.resetPassword();
    		return pw;
    	}

}
