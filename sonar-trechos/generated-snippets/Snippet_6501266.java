public abstract class Openable implements Runnable {
	
	private Matcher<?>[] shellMatchers;
	
	public Openable(Matcher<?>... shellMatchers){
		InstanceValidator.checkNotNull(shellMatchers, "shellMatchers");
		if(shellMatchers.length == 0){
			throw new IllegalArgumentException("shellMatchers cannot be empty!");
		}
		this.shellMatchers = shellMatchers;
	}
	
	public Matcher<?>[] getShellMatchers(){
		return this.shellMatchers;
	}

}
