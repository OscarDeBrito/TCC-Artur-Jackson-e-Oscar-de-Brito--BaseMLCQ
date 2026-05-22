public class Snippet__6493734 {

    public String addVariableOnClasspath(String name, String value, boolean overwriteIfExists) {
    		log.info("Adding variable on Classpath: " + name + "=" + value);
    		activateLibrariesTab();
    		getLibraryTree().getItem("Classpath").select();
    		return addVariableShared(name, value, overwriteIfExists);
    	}

}
