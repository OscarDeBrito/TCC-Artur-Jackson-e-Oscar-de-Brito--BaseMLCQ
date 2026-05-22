public class Snippet__6835472 {

    @Override
    	public Optional<URI> getProjectDescriptionLocation() {
    		if (checkExists() // Existing project AND
    				&& ((getLocation().isPlatformResource()
    						// Platform resource URI
    						&& URIUtils.isPlatformResourceUriPointingToProject(getLocation()))
    						|| isExternal())) { // OR external
    			return fromNullable(getLocation().appendSegment(IN4JSProject.PACKAGE_JSON));
    		} else {
    			return absent();
    		}
    	}

}
