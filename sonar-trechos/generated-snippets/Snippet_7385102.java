public class Snippet__7385102 {

    private void reportAvailableDependencies(InitializrServiceMetadata metadata,
    			StringBuilder report) {
    		report.append("Available dependencies:").append(NEW_LINE);
    		report.append("-----------------------").append(NEW_LINE);
    		List<Dependency> dependencies = getSortedDependencies(metadata);
    		for (Dependency dependency : dependencies) {
    			report.append(dependency.getId()).append(" - ").append(dependency.getName());
    			if (dependency.getDescription() != null) {
    				report.append(": ").append(dependency.getDescription());
    			}
    			report.append(NEW_LINE);
    		}
    	}

}
