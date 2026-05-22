public class Snippet__7520782 {

    @Override
    	public SecurityConfigurer<I> namenodePrincipal(String principal) {
    		if (StringUtils.hasText(principal)) {
    			hadoopSecurity.setNamenodePrincipal(principal);
    		}
    		return this;
    	}

}
