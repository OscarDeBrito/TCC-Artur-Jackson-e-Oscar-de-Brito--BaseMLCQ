public class Snippet__5417605 {

    public ProjectModel getModel(String projectName) {
    		ProjectModel projectModel = models.get(projectName);
    		if (projectModel == null) {
    			projectModel = new ProjectModel(projectName);
    			models.put(projectName, projectModel);
    		}
		
    		return projectModel;
    	}

}
