public class Snippet__7291569 {

    public boolean test(Object receiver, String property, Object[] args,
    			Object expectedValue) {
    		if (property.equals(IS_GRAILS_APP)) {
    			debug("isGrailsApp? "+receiver.getClass() +" = "+receiver);
    			IProject project = toProject(receiver);
    			debug("adapted to IProject = "+project);
    			boolean result = project!=null && GrailsNature.isGrailsAppProject(project);
    			debug("isGrailsApp? => "+result);
    			return result;
    		} 
    		return false;
    	}

}
